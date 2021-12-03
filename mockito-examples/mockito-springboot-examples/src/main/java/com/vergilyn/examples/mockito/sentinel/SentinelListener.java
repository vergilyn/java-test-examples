package com.vergilyn.examples.mockito.sentinel;

import java.time.LocalTime;
import java.util.List;

import javax.annotation.PostConstruct;

import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;

import org.springframework.stereotype.Component;

@Component
public class SentinelListener {
	private static final String RESOURCE = SentinelListener.class.getName();
	private static final int QPS_LIMIT = 4;

	@PostConstruct
	public void postConstruct(){
		// 配置规则.
		List<FlowRule> rules = Lists.newArrayList();
		FlowRule rule = new FlowRule();
		rule.setResource(RESOURCE);
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to X.
		rule.setCount(QPS_LIMIT);
		rules.add(rule);

		// 核心
		FlowRuleManager.loadRules(rules);

		System.out.println("loadRules >>>> " + FlowRuleManager.getRules().size());
	}

	public void handler(Integer index){
		if (SphO.entry(RESOURCE)){
			// 被保护的逻辑
			System.out.printf("[SphO][%s][%d] >>>> entry. \n", LocalTime.now(), index);

			// 可以不手动调用，等QPS自动释放
			// SphO.exit();
		}else {
			// 处理被流控的逻辑
			System.out.printf("[SphO][%s][%d] >>>> exceeded. \n", LocalTime.now(), index);
		}
	}
}
