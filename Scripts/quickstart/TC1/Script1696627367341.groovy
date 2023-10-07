import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*

import com.fasterxml.jackson.databind.JsonNode
import com.networknt.schema.JsonSchema
import com.networknt.schema.ValidationMessage

import my.JsonSchemaHelper

String schemaContent = """
{
	\"enum\":[1, 2, 3, 4],
	\"enumErrorCode\":\"Not in the list\"
}
"""

JsonSchemaHelper helper = new JsonSchemaHelper()
JsonSchema schema = helper.getJsonSchemaFromStringContent(schemaContent)
JsonNode node = helper.getJsonNodeFromStringContent("7")
Set<ValidationMessage> errors = schema.validate(node)
assertThat(errors.size(), is(1))