package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the commodity channel index (CCI) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class CCI extends TechnicalIndicatorResponse<IndicatorData> {

  private CCI(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code CCI} instance from json.
   *
   * @param json string to parse
   * @return CCI instance
   */
  public static CCI from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code CCI}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<CCI> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: CCI";
    }

    @Override
    CCI resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("ADX"))
      )));
      return new CCI(metaData, indicators);
    }
  }
}
