<h1 align="center">KettraShop</h1>

_"simples plugin de ativação de produtos da loja, dentro do Minecraft"_

# ⚙️ Configurações

caso você não temha uma loja virtual para seu servidor você podera comprar de mim por `R$ 131,98` entre em contato via discord: `SEBASTIAN JN ฅ^•ﻌ•^ฅ#7879`

**1 -** Para o plugin funciona você precisa colocar as informações corretas para se conectar ao MySql!
```yml
# conexao ao banco de dados
MySQL:
  host: ""
  usuario: ""
  senha: ""
  database: ""
```

**2 -** tempo para fazer a busca por novas compras aprovadas, recomendado ser mais de 5 minutos!
```yml
# configurações de tempo para fazer a busca no mysql!
tempo: 5
```

**3 -** notificação via webhook do discord, obrigatoriamente a usar esya configurações, na `url` você coloca o token/link do webhook e na `mensagem` o que o webhook vai aparecer ( @player é o nome do jogador )
```yml
# notificação via webhook do discord
webhook:
       url: ""
       mensagem: ""
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
