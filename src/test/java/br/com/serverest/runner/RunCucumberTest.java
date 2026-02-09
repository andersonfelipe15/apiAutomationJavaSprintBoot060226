package br.com.serverest.runner; // define o pacote responsável pelos runners de execução dos testes

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME; // constante usada para definir onde estão os steps e hooks
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME; // constante usada para configurar relatorios e formatos de saída

import org.junit.platform.suite.api.ConfigurationParameter; // permite passar parâmetros de configuracao para o Cucumber
import org.junit.platform.suite.api.IncludeEngines; // define qual engine de teste será utilizada
import org.junit.platform.suite.api.SelectClasspathResource; // define onde estão os arquivos .feature
import org.junit.platform.suite.api.Suite; // indica que esta classe e uma suíte de testes do JUnit

@Suite // marca a classe como uma suíte de execucao
@IncludeEngines("cucumber") // informa ao JUnit que a engine que irá rodar e o Cucumber
@SelectClasspathResource("features") // aponta que os arquivos feature estao na pasta resources/features
@ConfigurationParameter( // inicia um bloco de configuracao
        key = GLUE_PROPERTY_NAME, // define a propriedade de localizacao do glue (steps)
        value = "br.com.serverest.steps,br.com.serverest.utils" // pacotes onde o Cucumber deve procurar steps, hooks e utils
)
@ConfigurationParameter( // inicia configuração de plugins de saída
        key = PLUGIN_PROPERTY_NAME, // propriedade que define quais plugins/relatorios serao gerados
        value = "pretty, html:target/cucumber-report.html" // pretty = log bonito no console | html = gera relatorio em HTML
)
public class RunCucumberTest { // classe runner que dispara a execucao dos testes
} // nao precisa de metodos, apenas das anotacoes
