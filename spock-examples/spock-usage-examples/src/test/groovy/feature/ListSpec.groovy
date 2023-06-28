package feature

import com.vergilyn.examples.spock.ListService
import spock.lang.Specification

class ListSpec extends Specification{
    /**
     * 待单元测试方法：{@link ListService#list()}。
     * <p> 校验方法的返回值是否与 期望值相同。
     */
    def "test list equality"() {
        def listService = new ListService();
        when:
        List<Object> results = listService.list();

        then:
        // 1. 首先，检测集合大小是否相等
        results.size() == expecteds.size()
        // 2. 然后，它对 result 列表中的每个元素，都去 expected 列表中查找是否有相同的元素。
        //   这里的 "相同" 是指 firstName 和 lastName 属性都相同。
        //   如果找到了相同的元素，那么 findAll 方法会返回一个包含这个元素的列表。
        results.every { result ->
            // 不要求 顺序相同。
            expecteds.findAll {
                    it["ruleId"] == result["ruleId"]
                    && it["messageType"] == result["messageType"]
                    && it["successNum"] == result["successNum"]
                    && it["failureNum"] == result["failureNum"]
            }.size() > 0
        }

        where:
        expecteds = [
                // 定于 Map结构
                ["ruleId": 10302, "messageType": 110, "successNum": 2, "failureNum": 1],
                ["ruleId": 10854, "messageType": 131, "successNum": 54, "failureNum": 4],
                ["ruleId": 10851, "messageType": 131, "successNum": 6, "failureNum": 0],
                ["ruleId": 10853, "messageType": 131, "successNum": 5, "failureNum": 0],
                ["ruleId": 10302, "messageType": 100, "successNum": 17, "failureNum": 0],
        ]
    }
}
