package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the Hilbert transform, trend vs cycle mode (HT_TRENDMODE) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class HT_TRENDMODE extends TechnicalIndicatorResponse<IndicatorData> {

  private HT_TRENDMODE(final Map<String, String> metaData,
                       final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code HT_TRENDMODE} instance from json.
   *
   * @param json string to parse
   * @return HT_TRENDMODE instance
   */
  public static HT_TRENDMODE from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code HT_TRENDMODE}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<HT_TRENDMODE> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: HT_TRENDMODE";
    }

    @Override
    HT_TRENDMODE resolve(Map<String, String> metaData,
                         Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("TRENDMODE"))
      )));
      return new HT_TRENDMODE(metaData, indicators);
    }
  }
}
