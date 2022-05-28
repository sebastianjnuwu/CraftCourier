<h1 align="center">KettraShop</h1>

   _"simple store product activation plugin, inside Minecraft"_

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
    <a href="https://app.codacy.com/gh/sebastianjnuwu/KettraShop?utm_source=github.com&utm_medium=referral&utm_content=sebastianjnuwu/KettraShop&utm_campaign=Badge_Grade_Settings"><img alt="Codacy" src="https://api.codacy.com/project/badge/Grade/2fd1d707e84c4701abf3803cef49b751"/></a>
  <a href="https://discord.gg/NDzFeDp8YE"><img src="https://discordapp.com/api/guilds/893997835412971570/widget.png"></a>
</p>

**📭 Discord:** `SEBASTIANJN#7879`

# 📊 Bstats

![](https://bstats.org/signatures/bukkit/KettraShop.svg)

# ⚙️ settings

Download the plugin by clicking [on here](https://github.com/sebastianjnuwu/KettraShop/releases/tag/1.2), Soon after configure it on your server with the information of each item correctly!

**1 -** For the plugin to work you need to put the correct information to connect to MySql!
```yml
# conexao ao banco de dados
MySQL:
  host: ""
  usuario: ""
  senha: ""
  database: ""
```

**2 -** time to search for new approved purchases, recommended to be more than 5 minutes!
```yml
# configurações de tempo para fazer a busca no mysql!
tempo: 5
```

**3 -** notification via discord webhook, remembering it is mandatory to use, access the discord server create a webhook copy the url and paste in `url`, right after configure the webhook message ( @player is the buyer's name)
```yml
# notificação via webhook do discord
webhook:
       url: ""
       mensagem: ""
```

**4 -** the message that appears in the chat only for the player who made the purchase! (@player is the buyer's name)
```yml
# mensagem no chat que aparecera apenas para o jogador que fez a compra!
mensagem: ""
```

**5 -** following this same configuration for the other products: id, command! (@player is the buyer's name)
```yml
# produto id,commandos
1:
 commands:
 - dar @player produto
```

# 🔗 developers

 we use the `maven` compiler to compile our plugin so download it and `java 17`:
 ```
 apt-get install maven -y 
 ```
 
 after you have installed `java 17` and `maven` clone our repository:
 ```
git clone https://github.com/sebastianjnuwu/plugin
```

 ready now you can code at will! To compile the plugin just run:
 ```
 cd plugin && mvn install
```

the plugin will be in the `/target` folder, put it in the `plugins` folder on the server and turn it on!
