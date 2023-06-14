# <img align="center" src="https://cdn.discordapp.com/attachments/1078405618139922532/1078417147325333565/9037-eyespy.gif" width="32px"> Kettra Shop

<div>
 <p align="center">
  <img alt="profile" src="https://cdn.discordapp.com/attachments/1078405618139922532/1078407637349175396/1677182777554.png" width="72%"  />
  <br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-orange.svg"/></a>
  <a href="https://app.codacy.com/gh/sebastianjnuwu/KettraShop/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade"><img src="https://app.codacy.com/project/badge/Grade/c3793767a2cd498da1ae8dea9ebbba3c"/></a>
  <a href="https://discord.gg/NDzFeDp8YE"><img src="https://discordapp.com/api/guilds/893997835412971570/widget.png"></a>
  <br>
  <br>
   <i>"The <b>KettraShop</b> plugin allows players to receive products from the virtual store directly into their in-game inventory, facilitating the shopping experience."</i>
  
   - Discord webhook notification 
   - Custom message for each product 
   - Full/empty inventory check 
   - Executing commands automatically 
</p>
<div>

## 📖 About

 <b>Kettra Shop</b> is a plugin capable of searching for <i>approved</i> purchases and executing commands to deliver products to the configured server.

### ⚙️ config
 
 See some explanations on how to configure the plugin on your server and then how to integrate it into your virtual store.
 
 <strong>• Defining the mysql connection:</strong> It is important to note that the mysql settings like tables and columns must be correct and linked in the backend (virtual store), fill in the settings correctly in `config.yml`
 
 ```yml
 # MySql connection information
MySQL:
   host: 'database_host'
   password: 'database_password'
   user: 'database_user'
   database: 'database_name'
 ```
 
 <strong>• Configuring fetch time:</strong> This search time is essential as it will check the name of each player online, the appropriate time would be at least 2 minutes.
 
 ```yml
 # time to search for approved purchase (m)
time: 2 # minutes
 ```
 
 <strong>• Configure notification:</strong> configure your webhook to notify you in your discord when a product is delivered.
 
 ```yml
 # send notification to Discord 
webhook:
 enable: true # activated 
 message: "" # message to discord 
 url: "" #  webhook discord 
 ```
 <strong>• Customize messages:</strong> To make the plugin more customized, don't forget to configure the messages
 
 ```yml
 # personalized messages
message:
 inventory: "&cYour inventory is full! Unable to deliver your product."
 reload: "&aPlugin reloaded successfully!"
 permission: "&cYou do not have permission!"
 ```
 
 <strong>• Configuring the product:</strong> each number is the product identification id that must be configured both in the plugin and in the backend (virtual store)
 
 ```yml
 1:
 name: "product_name"
 inventory: false
 message: "&a you received @product!"
 commands:
 - say @player lol!
 ```
 
 ### 🏷️ integration 
 
 Integrate your store with a plugin through sequelize or something similar. Attention to the `status` and `id_product` of each product: 
 - `0` = purchase pending; 
 - `1` = approved purchase; 
 - `2` = purchase refused; 
 - `3` = purchase delivered and already approved.
 
```js
import Sequelize from 'sequelize';
import { sequelize } from '#connect';

const transaction = sequelize.define('TRANSACTION', {
  uuid: {
    type: Sequelize.STRING,
    primaryKey: true,
  },
  nick: { // Player's name 
    type: Sequelize.STRING,
  },
  email: {
    type: Sequelize.STRING,
  },
  id_product: { // Product id 
    type: Sequelize.BIGINT(1),
  },
  status: { // Status to buy
    type: Sequelize.BIGINT(1),
  },
  date: {
    type: Sequelize.DATE,
    defaultValue: Sequelize.NOW,
  },
});

transaction.sync();

export default transaction;
```
 
## 📊 Bstats

![](https://bstats.org/signatures/bukkit/KettraShop.svg)
