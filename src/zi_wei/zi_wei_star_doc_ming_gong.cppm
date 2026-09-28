// 紫微斗数星曜文档 - 十四主星守命条目契约
export module ZhouYi.ZiWei.StarDoc.MingGong;

import std;
import ZhouYi.GanZhi;

export namespace ZhouYi::ZiWei::StarDoc {
using namespace std;
using namespace ZhouYi::GanZhi;

/** @brief 守命条目内一段有明确主题的斗数解说。 */
struct MingGongAnalysisSection {
  /** @brief 段落主题，例如“性情取象”或“午宫入庙”。 */
  string title;
  /** @brief 按星曜、宫位与成立条件写成的中文解说。 */
  string content;
};

/**
 * @brief 十四主星在命宫守垣时的专用解说。
 *
 * @details 条目以主星、同宫主星与命宫地支共同匹配；星曜亮度、四化、辅煞及
 *          三方四正不写入固定断语，而由排盘结果在呈现时同参。
 */
struct MingGongDocument {
  /** @brief 守命条目标题，例如“紫微破军丑未守命宫”。 */
  string title;
  /** @brief 守命结构的简要纲领。 */
  string summary;
  /** @brief 按该星系与地支位置写成的守命摘要。 */
  string description;
  /** @brief 分主题守命断语；为空时仅呈现守命摘要。 */
  vector<MingGongAnalysisSection> sections;
};

/**
 * @brief 查询十四主星守命的地支与同宫组合条目。
 * @param stars 命宫十四主星名称；单星或双星均可，顺序不影响匹配。
 * @param branch 命宫地支。
 * @return 匹配到的守命条目；尚未登记的星系组合返回空值，由调用方采用通用星情。
 */
optional<MingGongDocument> get_ming_gong_document(const vector<string> &stars,
                                                  DiZhi branch);

} // namespace ZhouYi::ZiWei::StarDoc
