/**
 * 
 */
package com.availity.api.util;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.availity.api.model.User;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * @author mangeles
 *
 */
public class FileHelper {
	
	final static Logger LOG = LoggerFactory.getLogger(FileHelper.class);
	
	/**
	 * This method reads the file in csv format, and converts it to a Map object.
	 * 
	 * @param fileName
	 * @return Map object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, TreeSet<User>> convertFileToMap(String fileName) 
			throws IOException {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		
		ObjectReader oReader = csvMapper.readerFor(User.class).with(schema); 
		Map<String, TreeSet<User>> fileMap = new LinkedHashMap<String, TreeSet<User>>();
		TreeSet<User> users;   
		
		try {
			Reader reader = new FileReader(fileName); 
			MappingIterator<User> mi = oReader.readValues(reader); 
			while (mi.hasNext()) { 
				User currentUser = mi.next();
				String insuranceProvider = currentUser.getInsuranceProvider();
				currentUser.setInsuranceProvider(null);
				if (fileMap.containsKey(insuranceProvider)) {
					fileMap.get(
							insuranceProvider).add(currentUser);
				} else {
					users = new TreeSet<User>(Comparator.comparing(User::getLastName)
							.thenComparing(User::getFirstName));
					users.add(currentUser);
					fileMap.put(insuranceProvider, users);
				}
			}
			return fileMap;
			
		} catch (FileNotFoundException fnfe) {
			LOG.error("File not found", fnfe);
		} catch (RuntimeJsonMappingException jme) {
			LOG.error("Error during data marshalling", jme);
		} 

		return null;
	}
	
	/**
	 * This method serializes the Map object, creates the zip file for download.
	 * 
	 * @param fileMap
	 * @param response
	 * @throws IOException
	 */
	public static void downloadFileFromMap(
			Map<String,TreeSet<User>> fileMap, HttpServletResponse response)
			throws IOException {
		//Initialize mapper to convert to json string
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		
		//Iterate over Map.entrySet().
		//For each insurance provider list, create a json file,
		//and compress them to one zip file. 
		BufferedOutputStream fos = new BufferedOutputStream(response.getOutputStream());
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (Entry<String, TreeSet<User>> entry : fileMap.entrySet()) {
            ZipEntry zipEntry = new ZipEntry(
            		entry.getKey().replaceAll(" ", "_") + ".json");
            zipOut.putNextEntry(zipEntry); 
            byte[] data = mapper.writeValueAsString(
            		(TreeSet<User>) entry.getValue())
            		.getBytes();
            zipOut.write(data, 0, data.length);
            zipOut.closeEntry();
        }
        zipOut.close();
        fos.close();
	}

}
