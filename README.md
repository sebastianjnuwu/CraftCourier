<h1 align="center">KettraShop</h1>

_"simple store product activation plugin, inside Minecraft"_

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
    <a href="https://app.codacy.com/gh/sebastianjnuwu/KettraShop?utm_source=github.com&utm_medium=referral&utm_content=sebastianjnuwu/KettraShop&utm_campaign=Badge_Grade_Settings"><img alt="Codacy" src="https://api.codacy.com/project/badge/Grade/2fd1d707e84c4701abf3803cef49b751"/></a>
  <a href="https://discord.gg/NDzFeDp8YE"><img src="https://discordapp.com/api/guilds/893997835412971570/widget.png"></a>
</p>

# Bstats

![](https://bstats.org/signatures/bukkit/KettraShop.svg)

# ⚙️ settings

Download the plugin by clicking [on here](https://github.com/sebastianjnuwu/KettraShop/releases/tag/1.2), Soon after configure it on your server with the information of each item correctly!

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
