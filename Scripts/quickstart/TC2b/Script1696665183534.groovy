
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*

import com.fasterxml.jackson.databind.JsonNode
import com.networknt.schema.JsonSchema
import com.networknt.schema.ValidationMessage
import my.JsonSchemaHelper

JsonSchemaHelper helper = new JsonSchemaHelper()

// With automatic version detection
File schemaFile = new File("./src/test/fixtures/TC2_schema.json")
String schemaContent = schemaFile.text
JsonNode schemaNode = helper.getJsonNodeFromStringContent(schemaContent);
JsonSchema schema = helper.getJsonSchemaFromJsonNodeAutomaticVersion(schemaNode);

schema.initializeValidators(); // by default all schemas are loaded lazily. You can load them eagerly via
							   // initializeValidators()

JsonNode node = helper.getJsonNodeFromStringContent("{\"id\": \"2\"}");
Set<ValidationMessage> errors = schema.validate(node);
assertThat(errors.size(), is(1));
