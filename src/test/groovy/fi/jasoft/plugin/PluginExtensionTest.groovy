package fi.jasoft.plugin

import org.gradle.api.file.SourceDirectorySet
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test

/*
* Copyright 2013 John Ahlroos
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
class PluginExtensionTest extends PluginTestBase {

    @Test
    void isVaadinExtensionPresent(){
        assert project.extensions.vaadin instanceof VaadinPluginExtension
    }

    @Test
    void areVaadinPropertiesConfigured(){
        VaadinPluginExtension vaadin = project.extensions.vaadin;
        assert vaadin.widgetset == null
        assert vaadin.widgetsetGenerator == null
        assert vaadin.version == "7+"
        assert vaadin.servletVersion == "3.0"
        assert vaadin.debug == true
        assert vaadin.debugPort == 8000
        assert vaadin.manageWidgetset == true
        assert vaadin.manageDependencies == true
        assert vaadin.serverPort == 8080
        assert vaadin.jvmArgs == null
        assert vaadin.jrebel instanceof VaadinPluginExtension.JRebel
        assert vaadin.devmode instanceof VaadinPluginExtension.DevMode
        assert vaadin.plugin instanceof VaadinPluginExtension.VaadinPluginConfiguration
        assert vaadin.addon instanceof VaadinPluginExtension.Addon
        assert vaadin.gwt instanceof VaadinPluginExtension.GWT
        assert vaadin.mainSourceSet == null
        assert vaadin.push == false
    }

    @Test
    void testVaadinClosure(){
        project.vaadin{
            widgetset 'com.example.Widgetset'
            widgetsetGenerator 'com.example.generator'
            version  "7+"
            servletVersion "2.5"
            debug true
            debugPort 8000
            manageWidgetset true
            manageDependencies true
            serverPort 8080
            jvmArgs null
            mainSourceSet null
            push false
        }
        assert project.extensions.vaadin.widgetset == 'com.example.Widgetset'
    }

    @Test
    void areGWTPropertiesConfigured(){
        VaadinPluginExtension vaadin = project.extensions.vaadin
        assert vaadin.gwt instanceof VaadinPluginExtension.GWT
        assert vaadin.gwt.style == "OBF"
        assert vaadin.gwt.optimize == 0
        assert vaadin.gwt.logLevel == "INFO"
        assert vaadin.gwt.localWorkers == Runtime.getRuntime().availableProcessors()
        assert vaadin.gwt.draftCompile == false
        assert vaadin.gwt.strict == false
        assert vaadin.gwt.userAgent == null
        assert vaadin.gwt.jvmArgs == null
        assert vaadin.gwt.version == "2.3.0"
        assert vaadin.gwt.extraArgs == null
        assert vaadin.gwt.sourcePaths == ['client', 'shared'] as String[]
        assert vaadin.gwt.collapsePermutations == false
    }

    @Test
    void testGWTClosure(){
        project.vaadin.gwt {
                style "PRETTY"
                optimize 0
                logLevel "INFO"
                localWorkers Runtime.getRuntime().availableProcessors()
                draftCompile false
                strict false
                userAgent "ie8,ie9,gecko1_8,safari,opera"
                jvmArgs null
                version "2.3.0"
                extraArgs null
                sourcePaths (['client', 'shared'] as String[])
                collapsePermutations false
        }
        assert project.extensions.vaadin.gwt.style == "PRETTY"
    }

    @Test
    void areDevModePropertiesConfigured(){
        VaadinPluginExtension vaadin = project.extensions.vaadin
        assert vaadin.devmode instanceof VaadinPluginExtension.DevMode
        assert vaadin.devmode.noserver == false
        assert vaadin.devmode.superDevMode == false
        assert vaadin.devmode.bindAddress == '127.0.0.1'
        assert vaadin.devmode.codeServerPort == 9997
    }

    @Test
    void testDevModeClosure(){
        project.vaadin.devmode {
            noserver false
            superDevMode false
            bindAddress '0.0.0.0'
            codeServerPort 9997
        }
        assert project.extensions.vaadin.devmode.bindAddress == '0.0.0.0'
    }

    @Test
    void areVaadinPluginConfigurationPropertiesConfigured(){
        VaadinPluginExtension vaadin = project.extensions.vaadin
        assert vaadin.plugin instanceof VaadinPluginExtension.VaadinPluginConfiguration
        assert vaadin.plugin.terminateOnEnter == true
        assert vaadin.plugin.logToConsole == false
        assert vaadin.plugin.openInBrowser == true
    }

    @Test
    void testPluginConfigurationClosure(){
        project.vaadin.plugin {
            terminateOnEnter false
            logToConsole false
            openInBrowser true
        }
        assert project.extensions.vaadin.plugin.terminateOnEnter == false
    }

    @Test
    void areAddonPropertiesConfigured(){
        VaadinPluginExtension vaadin = project.extensions.vaadin
        assert vaadin.addon instanceof VaadinPluginExtension.Addon
        assert vaadin.addon.author == ''
        assert vaadin.addon.license == ''
        assert vaadin.addon.title == ''
    }

    @Test
    void testAddonClosure(){
        project.vaadin.addon {
            author 'Testing person'
            license ''
            title ''
        }
        assert project.extensions.vaadin.addon.author == 'Testing person'
    }

    @Test
    void areJRebelPropertiesConfigured(){
        VaadinPluginExtension vaadin = project.extensions.vaadin
        assert vaadin.jrebel instanceof VaadinPluginExtension.JRebel
        assert vaadin.jrebel.enabled == false
        assert vaadin.jrebel.location == null
    }

    @Test
    void testJRebelClosure(){
        project.vaadin.jrebel{
            enabled true
            location 'path/to/jrebel'
        }
        assert project.extensions.vaadin.jrebel.enabled
        assert project.extensions.vaadin.jrebel.location == 'path/to/jrebel'
    }
}
