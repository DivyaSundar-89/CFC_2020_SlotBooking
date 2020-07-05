from cloudant.client import Cloudant
from cloudant.error import CloudantException
from cloudant.result import Result

# IBM Cloudant Legacy authentication
client = Cloudant("41853c84-7969-4a90-8330-a577d695bc23-bluemix", "ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b", url="https://41853c84-7969-4a90-8330-a577d695bc23-bluemix:ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b@41853c84-7969-4a90-8330-a577d695bc23-bluemix.cloudantnosqldb.appdomain.cloud")
client.connect()

# IAM Authentication (uncomment if needed, and comment out previous IBM Cloudant Legacy authentication section)
# client = Cloudant.iam("<username>", "<apikey>")
# client.connect()

database_name = "cus"

my_database = client.create_database(database_name)

if my_database.exists():
    print("cus database successfully created")

sample_data = [
    [1, "one", "boiling", 100],
    [2, "two", "hot", 40],
    [3, "three", "warm", 20],
    [4, "four", "cold", 10],
    [5, "five", "freezing", 0]
]

# Create documents using the sample data.
# Go through each row in the array
for document in sample_data:
    # Retrieve the fields in each row.
    number = document[0]
    name = document[1]
    description = document[2]
    temperature = document[3]

    # Create a JSON document that represents
    # all the data in the row.
    json_document = {
        "numberField": number,
        "nameField": name,
        "descriptionField": description,
        "temperatureField": temperature
    }

    # Create a document using the Database API.
    new_document = my_database.create_document(json_document)

    # Check that the document exists in the database.
    if new_document.exists():
        print("Document successfully created.")

result_collection = Result(my_database.all_docs)


result_collection = Result(my_database.all_docs, include_docs=True)

client.disconnect()

