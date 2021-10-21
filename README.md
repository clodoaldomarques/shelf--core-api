# Introdução
Inicialmente iniciei esse projeto apenas para praticar alguns conceitos que ando estudando, como restfull ou clean code, por exemplo.
Contudo não estava afim de fazer simplismente um "Hello World", ai veio a idéia de montar um sistema de vendas de prateleira
daqueles bem genéricos, afinal cansei de passar vergonha quando me perguntava se eu tinha um. 
Este primeiro módulo é o "core", onde se detem as principais funcionalidades do controle de estoque.


# Iniciando no projeto
TODO: Informe aos usuários como executar o código em seu próprio sistema. Nesta sessão você pode explicar como:
1. Processo de instação
2. Dependencias do software
3. Última versão
4. API Referenciadas

# Build and Testes
TODO: Descreva como buildar e executar os testes em seu projeto

- [Padrões e Processos para Criação e Projetos]
- [Git Commit Messages]
- [Padrões de versionamento/repositórios]
- [Versionamento de Projeto]

## Padrão para formatação do código

Todos os desenvolvedores devem usar o [padrão de formatação (Style Guide) do Google](https://google.github.io/styleguide/javaguide.html).
Para automatizar esta formação, configure a IDE IntelliJ da seguinte maneira:

1. [Importe na IDE o XML](https://www.jetbrains.com/help/idea/copying-code-style-settings.html#) (`File > Settings > Editor > Code Style > Java > ⚙ icon > Import Schema > IntelliJ IDEA code style XML`)
   que se encontra na subpasta `config` projeto. Há versões para [IntelliJ](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) e [Eclipse](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml).
2. Habilite a [organização automática de import](https://www.jetbrains.com/help/idea/creating-and-optimizing-imports.html#) (`File > Settings > Editor > General > Auto Import > Optimize imports on the fly`)
3. Passe usar a [formatação de código](https://www.jetbrains.com/help/rider/Code_Formatting_Style.html), acesse o menu `Code` do IntelliJ ou utilize a tecla de atalho `Ctrl+Alt+Shift+L`.

## Analisador estático de código

Este projeto utiliza o [plugin pmd](https://docs.gradle.org/current/userguide/pmd_plugin.html) do gradle para análise estática do código seguindo as [regras de qualidade](https://pmd.github.io/latest/pmd_userdocs_tools_gradle.html) definida no arquivo `config/custom-pmd-ruleset.xml` deste projeto.
O processo de build irá falhar caso alguma dessas regras sejam violadas. Faça a verificação de qualidade com o comando `gradle check`.
