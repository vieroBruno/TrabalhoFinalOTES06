  <h1>📱 Aplicativo de Clima (Android + Java REST API)</h1>

  <h2>🎯 Sobre o Projeto</h2>
    <p>
        Este projeto foi desenvolvido como parte do trabalho da disciplina  OTES06 (Programação para dispositivos móveis), cujo objetivo é criar um aplicativo móvel completo, 
        envolvendo <strong>front-end</strong>, <strong>back-end</strong> e <strong>comunicação via API REST</strong>.
    </p>
    <p>
        O aplicativo criado consiste em um <strong>app de clima</strong>, permitindo 
        ao usuário visualizar informações meteorológicas da sua região ou de cidades pesquisadas.
    </p>

   <hr>

   <h2>🛠️ Arquitetura Geral</h2>

   <h3>1. Aplicativo Android (Front-end)</h3>
    <p>
        Desenvolvido nativamente em Java, utilizamos a versão do Android Studio Otter para a confecção do frontend. 
      Utilizamos ícones nativos da própria IDE e também ícones baixados da internet para fazer a estilização.
      O backend cria um servidor que consome a api <a target="_blank" href="https://openweathermap.org/guide">OpenWeatherMap Guide</a>
    </p>

   <hr>

   <h2>🌐 Servidor e Rotas da API</h2>
   <p>A comunicação ocorre via HTTP utilizando o padrão <strong>REST</strong> por meio de requisições GET.</p>
   
   <h3>1. Buscar clima por nome da cidade</h3>
    <pre><code>GET /api/weather/{cityName}</code></pre>

   <h3>2. Buscar clima por latitude e longitude</h3>
    <pre><code>GET /api/weather/coords?lat={latitude}&lon={longitude}</code></pre>

   <hr>

   <h2>📲 Funcionalidades do Aplicativo</h2>

   <h3>🔹 1. Detecção automática de localização</h3>
    <p>
        Ao abrir o app pela primeira vez, o usuário é solicitado a conceder permissão de GPS.
        Após a permissão, o app obtém latitude e longitude automaticamente e realiza a requisição 
        para a rota correspondente, exibindo o clima da região atual.
    </p>

   <h3>🔹 2. Pesquisa por cidade</h3>
    <p>
        Há um campo de texto na parte superior da interface. O usuário pode digitar o nome da cidade
        e clicar no botão de pesquisa para realizar uma requisição à API pelo nome da cidade.
    </p>

   <hr>

   <h2>☁️ Informações exibidas</h2>
    <p>O aplicativo exibe as seguintes informações:</p>
    <ul>
        <li>Nome da cidade</li>
        <li>Temperatura atual</li>
        <li>Sensação térmica</li>
        <li>Temperaturas máxima e mínima</li>
        <li>Descrição do clima</li>
        <li>Visibilidade</li>
        <li>Umidade</li>
        <li>Pressão atmosférica</li>
        <li>Velocidade do vento</li>
        <li>Horário do nascer do sol</li>
        <li>Horário do pôr do sol</li>
    </ul>

   <hr>

   <h2>⚠️ Tratamento de Erros</h2>

   <h3>404 — Cidade não encontrada</h3>
    <p>Exibido quando o back-end não encontra informações para o nome pesquisado.</p>

   <h3>500 — Servidor indisponível</h3>
    <p>Mostrado quando a API não está rodando ou ocorre um erro interno no servidor.</p>

   <h3>Permissão negada</h3>
    <p>
        Caso o usuário negue o acesso ao GPS, o app não consegue buscar o clima atual e 
        exibe instruções sobre como habilitar manualmente a permissão.
    </p>

  <h3>Região não encontrada</h3>
    <p>
        Caso o usuário conceda acesso ao GPS, mas o servidor não encontre uma cidade especificada para esta coordenada, o aplicativo mostrara um erro.
    </p>

   <hr>

 <h2> Para utilizar </h2>
 <p>Neste link temos uma demonstração do funcionamento do aplicativo: <a href="https://drive.google.com/file/d/1sgYdHr3usFNwDSaph1jhWpboOeYOV1h_/view?usp=sharing">Google Drive</a></p>
 <p>
Para testar o aplicativo, basta importar o projeto deste repositório, iniciar o servidor localizado na pasta <em>backend</em> 
(é possível verificar seu funcionamento acessando <strong><a href="http://localhost:8081/api/weather/florianopolis">https://localhost:8081</strong>), e então executar o aplicativo Android.
</p>
 <h3>Considerações</h3>
 <p>Para que o funcionamento da funcionalidade de busca por GPS funcione, é necessário popular o histórico do dispositivo com uma localização antes. Isto pode ser feito facilmente ao entrar no Google Maps,
 por exemplo, até verificar que o círculo no mapa ficará azul, conforme na foto: </p>
 <img width="266" height="572" alt="image" src="https://github.com/user-attachments/assets/cb40e50a-3328-473a-bfbd-b2c719d62c99" />
 <p>Inicialmente os emuladores provavelmente estarão em Mountain View, sede do Google. Caso você queira simular uma outra localização, é possível configurar 
   ao clicar nos três pontinhos do emulador -> Extended Controls, que abrirá o menu mostrado na imagem. Após abrir, basta configurar um local e clicar no botão "Set Location"</p>
   <img width="855" height="815" alt="image" src="https://github.com/user-attachments/assets/34d16c76-4bfa-4f78-989b-db7acd9d192f" />
   
   <p>É importante ressaltar também que esta requisição é sempre feita no momento de Criação do aplicativo, ou seja, sempre que ele é fechado completamente e aberto novamente. Contudo, o Android Studio não permite
   fechar o aplicativo completamente e reabri-lo pois o fechamento encerra sua execução, portanto caso queira mudar de localização, além de alterar no menu Extended Controls também é preciso reiniciar a aplicação.</p>


 <hr>
   <h2>📄 Considerações Finais</h2>
    <p>
     Neste trabalho aprendemos muito sobre a criação e o consumo de rotas de API, bem como sobre a utilização do Android Studio no desenvolvimento de aplicativos para dispositivos móveis.
      Nossa maior dificuldade foi nos adaptar à ferramenta, que por exigir muito processamento acabou apresentando travamentos. 
      No entanto, ao longo do desenvolvimento fomos nos familiarizando cada vez mais com a plataforma. Também buscamos adicionar funcionalidades variadas, como o uso de toasts, do AndroidManifest e de threads,
      para explorar e testar melhor os recursos do Android Studio.
    </p>
    <p> Qualquer dúvida que houver referente ao Projeto, por favor enviar para jpjpsell@gmail.com</p>
