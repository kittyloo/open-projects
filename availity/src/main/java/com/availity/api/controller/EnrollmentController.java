/**
 * 
 */
package com.availity.api.controller;

import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.availity.api.model.User;
import com.availity.api.util.FileHelper;

/**
 * @author mangeles
 *
 */
@RestController
@RequestMapping("api/enrollment")
public class EnrollmentController {
	
	final Logger LOG = LoggerFactory.getLogger(EnrollmentController.class);
	
	/**
	 * @api {get} /api/enrollment/parser Load Reader
	 * @apiVersion 0.0.1
	 * @apiGroup Enrollment
	 * @apiName LoadReader
	 * @apiParam {String} filename The CSV file
	 * @apiDescription This method reads contents of the supplied csv file, and creates one zip file to download, 
	 * which contains the multiple files based on the different insurance providers.
	 * If a file is not provided, it will use the sample csv file, by default (for demo purposes)
	 * 
	 * @apiExample Example usage:
	 * 		http://localhost:8000/api/enrollment/parser?fileName=/Users/marikitangeles/Documents/enrollment-list-copy.csv
	 * @apiSuccess {application/zip} The Zip file (parsedData.zip)
	 * @return
	 */
	@RequestMapping(value = "/parser", method = RequestMethod.GET, produces="application/zip")
	public String loadReader(
			@RequestParam(value = "fileName", required = false) String fileName,
			HttpServletResponse response) {

		try {
			//If a file is not provided, it will use the sample csv file, by default
			//(for demo purposes)
			if (StringUtils.isEmpty(fileName)) {
				fileName = "enrollment-list.csv";
			}
			LOG.info("file name: " + fileName);
			
			//Convert File contents to Map Object
			Map<String, TreeSet<User>> usersByProvider = 
					FileHelper.convertFileToMap(fileName);
			if (usersByProvider != null 
					&& usersByProvider.size() > 0) {
				//Set response object
				response.setStatus(HttpServletResponse.SC_OK);
			    response.addHeader("Content-Disposition", "attachment; filename=\"parsedData.zip\"");

			    //Create zip file and download
			    FileHelper.downloadFileFromMap(usersByProvider, response);
			}

		} catch (Exception e) {
            LOG.error("Exception", e);
        }
		return "parsedData.zip is now downloaded.";
    }

	/**
	 * @api {get} /api/enrollment/status Get Status
	 * @apiVersion 0.0.1
	 * @apiGroup Enrollment
	 * @apiName GetStatus
	 * @apiDescription This method is a sample api, and returns a success status
	 * @apiExample Example usage:
	 * 		http://localhost:8000/api/enrollment/status
	 * @return
	 */
	@RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getStatus() {
        return "Enrollment parser service is running!";
    }
	

}
