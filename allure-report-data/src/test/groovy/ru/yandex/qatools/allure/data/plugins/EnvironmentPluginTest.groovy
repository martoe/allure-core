package ru.yandex.qatools.allure.data.plugins

import org.junit.Test
import ru.yandex.qatools.commons.model.Environment
import ru.yandex.qatools.commons.model.Parameter

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 07.02.15
 */
class EnvironmentPluginTest {

    def plugin = new EnvironmentPlugin()

    @Test
    void shouldSaveInfo() {
        def parameter = new Parameter(name: "pname", key: "pkey", value: "pvalue")
        def env = new Environment(id: "id", name: "name", parameter: [
                parameter
        ])
        plugin.process(env)

        assert plugin.environment
        assert plugin.environment.id == "id"
        assert plugin.environment.name == "name"

        assert plugin.environment.parameter.size() == 1
        assert plugin.environment.parameter.contains(parameter)
    }

    @Test
    void shouldNotUpdateIdIfNull() {
        def id = plugin.environment.id
        def name = plugin.environment.name

        def parameter = new Parameter(name: "pname", key: "pkey", value: "pvalue")
        def env = new Environment(name: "name", parameter: [
                parameter
        ])
        plugin.process(env)

        assert plugin.environment
        assert plugin.environment.id == id
        assert plugin.environment.name == name

        assert plugin.environment.parameter.size() == 1
        assert plugin.environment.parameter.contains(parameter)
    }

    @Test
    void shouldGetRightType() {
        assert plugin.type == Environment
    }

    @Test
    void shouldGetRightDataName() {
        assert plugin.pluginData
        assert plugin.pluginData.name == ["environment.json"]
    }
}
