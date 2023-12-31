import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode
import com.networknt.schema.JsonSchema
import com.networknt.schema.ValidationMessage

import my.JsonSchemaHelper

/**
 * https://www.baeldung.com/introduction-to-json-schema-in-java
 */

 String data = '''
{
    "id": 1,
    "name": "Lampshade",
    "price": 0
}
'''

String schemaContent = '''
{
    "$schema": "http://json-schema.org/draft-04/schema#",
    "title": "Product",
    "description": "A product from the catalog",
    "type": "object",
    "properties": {
        "id": {
            "description": "The unique identifier for a product",
            "type": "integer"
        },
        "name": {
            "description": "Name of the product",
            "type": "string"
        },
        "price": {
            "type": "number",
            "minimum": 0,
            "exclusiveMinimum": true
        }
    },
    "required": ["id", "name", "price"]
}
'''

JsonSchemaHelper helper = new JsonSchemaHelper()
JsonSchema schema = helper.getJsonSchemaFromStringContent(schemaContent)
JsonNode node = helper.getJsonNodeFromStringContent(data)
Set<ValidationMessage> errors = schema.validate(node)
assertThat(errors.size(), is(1))
assertThat(errors).isNotEmpty().asString().contains("price: must have a minimum value of 0")
