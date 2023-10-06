package my

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.networknt.schema.JsonSchema
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion

public class JsonSchemaHelper {
	
	private ObjectMapper mapper = new ObjectMapper()
	
	public JsonSchema getJsonSchemaFromStringContent(String schemaContent) {
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4)
		return factory.getSchema(schemaContent)
	}
	
	public JsonNode getJsonNodeFromStringContent(String content) throws IOException {
		return mapper.readTree(content);
	}
}
