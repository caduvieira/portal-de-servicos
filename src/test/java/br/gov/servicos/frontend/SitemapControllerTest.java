package br.gov.servicos.frontend;

import br.gov.servicos.cms.PaginaEstaticaRepository;
import br.gov.servicos.cms.PaginaTematicaRepository;
import br.gov.servicos.orgao.OrgaoRepository;
import br.gov.servicos.servico.ServicoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SitemapControllerTest {

    @Mock
    ServicoRepository servicos;

    @Mock
    OrgaoRepository orgaos;

    @Mock
    PaginaEstaticaRepository estaticas;

    @Mock
    PaginaTematicaRepository tematicas;

    SitemapController controller;

    @Before
    public void setUp() throws Exception {
        controller = new SitemapController(servicos, orgaos, tematicas, estaticas);
    }

    @Test
    public void renderizaSitemap() throws Exception {
        assertThat(controller.sitemap().getViewName(), is("sitemap"));
    }

    @Test
    public void adicionaTodosOsServicosAoModelo() throws Exception {
        Map<String, Object> model = controller.sitemap().getModel();
        assertThat(model, hasKey("servicos"));
        verify(servicos).findAll();
    }

    @Test
    public void adicionaTodosOsOrgaosAoModelo() throws Exception {
        Map<String, Object> model = controller.sitemap().getModel();
        assertThat(model, hasKey("orgaos"));
        verify(orgaos).findAll();
    }

    @Test
    public void adicionaTodosOsSegmentosDaSociedadeAoModelo() throws Exception {
        Map<String, Object> model = controller.sitemap().getModel();
        assertThat(model, hasKey("publicosAlvo"));
    }

    @Test
    public void adicionaTodasAsAreasDeInteresseAoModelo() throws Exception {
        Map<String, Object> model = controller.sitemap().getModel();
        assertThat(model, hasKey("areasDeInteresse"));
    }

}