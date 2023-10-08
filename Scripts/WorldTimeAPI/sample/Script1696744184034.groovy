import com.fasterxml.jackson.databind.JsonNode
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.networknt.schema.JsonSchema
import com.networknt.schema.ValidationMessage

import my.JsonSchemaHelper

/**
 * validate a JSON returned by 
 *     http://worldtimeapi.org/api/ip
 * with JSON Schema using the JSON validator provided by networknt 
 */

String data = '''
{
    "abbreviation": "JST",
    "client_ip": "163.131.26.171",
    "datetime": "2023-10-01T22:15:18.742317+09:00",
    "day_of_week": 0,
    "day_of_year": 274,
    "dst": false,
    "dst_from": null,
    "dst_offset": 0,
    "dst_until": null,
    "raw_offset": 32400,
    "timezone": "Asia/Tokyo",
    "unixtime": 1696166118,
    "utc_datetime": "2023-10-01T13:15:18.742317+00:00",
    "utc_offset": "+09:00",
    "week_number": 39
}
'''

String schemaContent = '''
{
    "$schema": "http://json-schema.org/draft-04/schema#",
    "title": "Request the current time based on your public IP (as JSON) by the WorldTimeAPI",
    "description": "See https://worldtimeapi.org/pages/schema",
    "type": "object",
    "properties": {
        "abbreviation": {
            "description": "the abbreviated name of the timezone",
            "type": "string"
        },
        "client_ip": {
            "description": "the IP address of the client",
            "type": "string"
        },
        "datetime": {
            "description": "an ISO8601-valid string representing the current, local date/time",
            "type": "string"
        },
        "day_of_week": {
            "description": "current day number of the week, where sunday is 0",
            "type": "integer",
            "minimum": 0,
            "exclusiveMinimum": false
        },
        "day_of_year": {
            "description": "ordinal date of the current year",
            "type": "integer"
        },
        "dst": {
            "description": "flag indicating wither the local time is in daylight savings",
            "type": "boolean"
        },
        "dst_from": {
            "description": "an ISO-8601-valid string representing the datetime when daylight savings started for this timezone",
            "type": ["string", "null"]
        },
        "dst_offset": {
            "description": "the difference in seconds between the current local time and daylight saving time for the location",
            "type": "integer"
        },
        "dst_until": {
            "description": "an ISO8601-valid string representing the datetime when daylight saving will end for this timezone",
            "type": ["string", "null"] 
        },
        "raw_offset": {
            "description": "the difference in seconds betweeen the current local time and the time in UTC, excluding daylight saving difference (see dst_offset)",
            "type": "integer"
        },
        "timezone": {
            "description": "timezone in Area/Location or Area/Location/Region format",
            "type": "string",
            "enum": ["Asia/Tokyo", "Asia/Ho_Chi_Minh", "America/Los_Angeles"]
        },
        "unixtime": {
            "description": "number of seconds since the Epoch",
            "type": "integer"
        },
        "utc_datetime": {
            "description": "an ISO8601-valid string representing the current date/time in UTC",
            "type": "string"
        },
        "utc_offset": {
            "description": "an IOS8601-valid string representing the offset from UTC",
            "type": "string"
        },
        "week_number": {
            "description": "the current week number",
            "type": "integer"
        }
    },
    "required": [
        "abbreviation",
        "datetime",
        "day_of_week",
        "day_of_year",
        "dst",
        "dst_from",
        "dst_offset",
        "dst_until",
        "raw_offset",
        "timezone",
        "unixtime",
        "utc_datetime",
        "utc_offset",
        "week_number"
    ]
}
'''

JsonSchemaHelper helper = new JsonSchemaHelper()
JsonSchema schema = helper.getJsonSchemaFromStringContent(schemaContent)
JsonNode node = helper.getJsonNodeFromStringContent(data)
Set<ValidationMessage> errors = schema.validate(node)
println errors
WebUI.verifyEqual(0, errors.size())
