package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.CadastroFeedbackCidadaoService;

import java.util.List;
import java.util.Map;

public class CadastroFeedbackCidadaoSteps {
    CadastroFeedbackCidadaoService cadastroFeedbackCidadaoService = new CadastroFeedbackCidadaoService();

    @Dado("que eu tenha os seguintes dados do feedback")
    public void queEuTenhaOsSeguintesDadosDoFeedback(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            cadastroFeedbackCidadaoService.setFieldsFeedbackCidadao(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de feedback")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeFeedback(String endPoint) {
        cadastroFeedbackCidadaoService.createFeedbackCidadao(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroFeedbackCidadaoService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroFeedbackCidadaoService.gson.fromJson(
                cadastroFeedbackCidadaoService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }
}
