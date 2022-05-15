<h1 align="center">KettraShop</h1>

_"simples plugin de ativação de produtos da loja, dentro do Minecraft"_

# ⚙️ Configurações

KettraShop é um plugin indicado para ativar os produtos da sua loja virtul do Minecraft ( cash, money etc...), caso não tenha um loja virtual faça uma ou compre de mim por `R$ 131,98`.

Para as pessoas que iram criar a loja virtual do servidor de minecraft é para colocar a tabela igual a esta logo abaixo e caso a comora for aprovada o `status 2` assim o plugin ativara os produtos automaticamente!
![](https://media.discordapp.net/attachments/969290884300537868/975371500775411752/Screenshot_20220515-091425.jpg)

Para o plugin funciona você precisa colocar as informações corretas para se conectar ao MySql!
```yml
# conexao ao banco de dados
MySQL:
  host: ""
  usuario: ""
  senha: ""
  database: ""
```

# 🔗 Desenvolvedores

 usamos o compilador `maven` para compilar nosso plugin então baixe ele e o `java 17`:
 ```
 apt-get install maven -y 
 ```
 
 depois de ter instalado o `java 17` e o `maven` clone o nosso repositório:
 ```
git clone https://github.com/sebastianjnuwu/plugin
```

 pronto agora você pode codar a vontade! para compilar o plugin basta executar:
 ```
 cd plugin && mvn install
```

o plugin estara na pasta `/target`, coloque na pasta `plugins` do servidor e ligue!
