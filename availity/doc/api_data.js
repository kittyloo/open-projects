define({ "api": [  {    "type": "get",    "url": "/api/enrollment/status",    "title": "Get Status",    "version": "0.0.1",    "group": "Enrollment",    "name": "GetStatus",    "description": "<p>This method is a sample api, and returns a success status</p>",    "examples": [      {        "title": "Example usage:",        "content": "http://localhost:8000/api/enrollment/status",        "type": "json"      }    ],    "filename": "/Users/marikitangeles/Documents/projects/availity/doc/EnrollmentController.java",    "groupTitle": "Enrollment"  },  {    "type": "get",    "url": "/api/enrollment/parser",    "title": "Load Reader",    "version": "0.0.1",    "group": "Enrollment",    "name": "LoadReader",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "String",            "optional": false,            "field": "filename",            "description": "<p>The CSV file</p>"          }        ]      }    },    "description": "<p>This method reads contents of the supplied csv file, and creates one zip file to download, which contains the multiple files based on the different insurance providers. If a file is not provided, it will use the sample csv file, by default (for demo purposes)</p>",    "examples": [      {        "title": "Example usage:",        "content": "http://localhost:8000/api/enrollment/parser?fileName=/Users/marikitangeles/Documents/enrollment-list-copy.csv",        "type": "json"      }    ],    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "type": "application/zip",            "optional": false,            "field": "The",            "description": "<p>Zip file (parsedData.zip)</p>"          }        ]      }    },    "filename": "/Users/marikitangeles/Documents/projects/availity/doc/EnrollmentController.java",    "groupTitle": "Enrollment"  },  {    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "optional": false,            "field": "varname1",            "description": "<p>No type.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "varname2",            "description": "<p>With type.</p>"          }        ]      }    },    "type": "",    "url": "",    "version": "0.0.0",    "filename": "/Users/marikitangeles/Documents/projects/availity/doc/main.js",    "group": "_Users_marikitangeles_Documents_projects_availity_doc_main_js",    "groupTitle": "_Users_marikitangeles_Documents_projects_availity_doc_main_js",    "name": ""  }] });
