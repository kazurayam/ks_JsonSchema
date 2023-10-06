import com.networknt.schema.JsonSchema

import my.JsonSchemaHelper

String schemaContent = "{\"enum\":[1, 2, 3, 4],\"enumErrorCode\":\"Not in the list\"}"

JsonSchema schema = JsonSchemaHelper.getJsonSchemaFromStringContent(schemaContent)