# 🎬 TheMovieDbApp

Aplicativo Android que consome a API do [The Movie Database (TMDb)](https://www.themoviedb.org/) para exibir informações sobre filmes e séries.

> Este projeto foi gerado a partir do [juanunix/AndroidAppTemplate](https://github.com/juanunix/AndroidAppTemplate).

![master](https://github.com/user-attachments/assets/226eff42-5c1e-4083-8b52-4d42f808ed40)

![detail](https://github.com/user-attachments/assets/29c3d349-9af5-4910-98d4-edd1d102931e)

## 🛠 Tecnologias e ferramentas utilizadas

- **Kotlin** – Linguagem principal do projeto ✅  
- **MVVM** – Arquitetura desacoplada baseada em ViewModel ✅  
- **MVI** – Padrão de arquitetura reativa (Model-View-Intent) ✅  
- **Hilt** – Injeção de dependência ⏳  
- **Retrofit** – Comunicação com APIs REST ✅  
- **Kotlin Coroutines** – Operações assíncronas ✅  
- **StateFlow e LiveData** – Gerenciamento de estado reativo ✅  
- **Room** – Persistência de dados local ✅  
- **Detekt** – Análise estática de código ✅  
- **Gradle com Kotlin DSL** – Sistema de build moderno e tipado ✅  
- **GitHub Actions** – Integração contínua automatizada ✅  
- **Renovate** – Gerenciamento automático de dependências ✅  
- **Danger** – Controle de qualidade em Pull Requests ✅  

## 📁 Estrutura de diretórios

```
TheMovieDbApp/
├── app/                      # Código principal do aplicativo Android
│   ├── core/                 # Códigos e utilitários compartilhados
│   ├── feature/              # Funcionalidades (módulos) como home, search, detail
│   ├── navigation/           # Gerenciamento de navegação
│   ├── presentation/         # Telas e UI usando Jetpack Compose
│   └── di/                   # Injeção de dependência
├── buildscripts/            # Scripts customizados para build
├── config/                  # Configuração do Detekt e outras ferramentas
│   └── detekt/
├── documentation/           # Documentação técnica do projeto
├── git-hooks/               # Hooks de git para lint, testes etc.
├── gradle/                  # Configurações e tarefas auxiliares do Gradle
├── apikey.properties        # Arquivo local com chave da API
└── README.md                # Este arquivo
```

## 🚀 Como começar

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/juanunix/TheMoviedbApp.git
   ```

3. **Configure o backend necessário**:

   O aplicativo depende também de um **backend próprio** que deve ser baixado e executado localmente.  
   Verifique a documentação e repositório correspondente para mais detalhes.

5. **Execute o projeto**:

   Abra o projeto no Android Studio e rode em um emulador ou dispositivo real.

## ✅ Funcionalidades

- Visualização de filmes e séries populares  
- Busca por título  
- Tela de detalhes com informações completas  
- Interface amigável e responsiva  

## 🧪 Testes

> O projeto ainda **não possui testes implementados**.  
Há espaço para adicionar:

- **Testes unitários** com JUnit e MockK  
- **Testes de instrumentação** com Espresso  
- **Testes de ponta a ponta (E2E)** com UI Automator ou frameworks externos como Maestro ou Detox  

---

## 🚧 Melhorias em andamento / futuras

- 🔧 **Correção de bug**: Corrigir problema no carregamento das "filmes em promoção" (provavelmente um erro na requisição ou mapeamento da resposta da API).  
- 🎨 **Criação de um Design System**: Padronizar cores, tipografia, espaçamentos e componentes para facilitar a manutenção da UI.  
- 🧩 **Modularização**: Separar os domínios principais (ex: filmes, séries, pesquisa) em módulos para melhorar a escalabilidade e tempo de build.  
- 📱 **Ajustes de responsividade**: Garantir que a interface funcione corretamente em diferentes tamanhos de tela e orientações.  
- 🌍 **Possível migração para Kotlin Multiplatform (KMP)**: Tornar a lógica de negócio multiplataforma e reaproveitar em iOS ou Web futuramente.  

## 📄 Licença

Este projeto está licenciado sob os termos da Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais informações.
