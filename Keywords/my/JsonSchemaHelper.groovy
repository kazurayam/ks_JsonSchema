package my

import com.networknt.schema.JsonSchema
import com.networknt.schema.JsonSchemaFactory
import com.networknt.schema.SpecVersion

public class JsonSchemaHelper {
	public static JsonSchema getJsonSchemaFromStringContent(String schemaContent) {
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4)
		return factory.getSchema(schemaContent)
	}
}
