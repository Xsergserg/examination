An application that allows uploading and getting a csv file.

The first line of the file are the headers!

Note: The field code is unique

The application allows interaction through a Rest endpoint that allows the user to:

● upload the data (POST /api/v1/upload - file as a parameter requested)

● Fetch all data (GET /api/v1/records)

● Fetch by code (GET /api/v1/records/{code})

● Delete all data (DELETE /api/v1/records)

Uploaded data store to a database. In memory database table contains the fields as
described on the csv file
