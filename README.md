<h1 align="center">KettraShop</h1>

_"simples plugin de ativação de produtos da loja, dentro do Minecraft"_

# Bstats
![](https://bstats.org/signatures/bukkit/KettraShop.svg)

# ⚙️ Configurações

Baixe o plugin clicando [aqui](https://github.com/sebastianjnuwu/KettraShop/releases/tag/1.2), logo configure em seu servidor!

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

**4 -** a mensagem que aparecera no chat apenas para o jogador que fez a compra! ( @player é o nome do jogador )
```yml
# mensagem no chat que aparecera apenas para o jogador que fez a compra!
mensagem: ""
```

**5 -** seguindo essa mesma configuração para os outros produtos: id, comando! ( @player é o nome do jogador )
```yml
# produto id,commandos
1:
 commands:
 - dar @player produto
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
