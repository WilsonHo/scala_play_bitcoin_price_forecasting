package bao.ho.models

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import play.api.libs.json.Json

case class Currency @JsonCreator()(@JsonProperty("id") id: Int,
                                   @JsonProperty("currency") currency: String,
                                   @JsonProperty("abbreviation") abbreviation: String,
                                   @JsonProperty("currency_type") currencyType: String)

object Currency {
  implicit val currencyJsonFormat = Json.format[Currency]
  implicit val currencyJsonWrite = Json.writes[Currency]
}