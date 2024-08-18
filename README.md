# CraftCourier

<div align="center">
  <img alt="profile" src="https://raw.githubusercontent.com/sebastianjnuwu/craftcourier/plugin/.github/icon.png" width="50%"  />
 
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-yellow.svg"/></a>
  <a href="https://app.codacy.com/gh/sebastianjnuwu/KettraShop/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade"><img src="https://app.codacy.com/project/badge/Grade/c3793767a2cd498da1ae8dea9ebbba3c"/></a>
</div>

<i>🛒 CraftCourier automates the delivery of items purchased in Minecraft virtual stores, ensuring fast and secure deliveries integrated with nLogin.</i>

- Discord webhook notifications
- Full/empty inventory checks
- Automatic command execution

## 📖 About

**CraftCourier** is an advanced plugin designed to seamlessly manage and execute the delivery of purchased items in Minecraft servers. It efficiently tracks approved purchases and automatically executes the necessary commands to deliver products directly to players.

- Download the plugin by clicking [here!](https://github.com/sebastianjnuwu/craftcourier/releases)

## ⚙️ Config

To configure the plugin, edit the `config.yml` file located in the `plugins/CraftCourier` directory. Below is a sample configuration:

```yaml
# Database configuration
database:
  hostname: "localhost:3306"  # The hostname and port of your MySQL server
  database: "database_name"    # The name of the database to use
  username: "username"         # The username to connect to the database
  password: "password"         # The password to connect to the database

# Webhook configuration
webhook:
  enable: false   # Enable or disable webhook notifications
  url: ""         # The URL to send webhook notifications to
  message: ""     # The message to send via the webhook when a purchase is processed

# Table configuration for transactions
table:
  transaction:
    table-name: "TRANSACTIONS"  # The name of the transactions table in your database
    columns:
      nickname: "nickname"      # The column name for the player's nickname
      status: "status"          # The column name for the transaction status
      uuid: "uuid"              # The column name for the player's UUID
    status: 
      default: 0                # Default status code for transactions
      approved: 1               # Status code for approved transactions
      received: 2               # Status code for received transactions

# Bonus configuration
bonus:
  enable: true   # Enable or disable the bonus feature
  amount: 2 # The number of purchases required to receive a bonus
  message: "&aCongratulations, you have received a bonus thanks to purchases made on the server!"  # The message sent to the player when they receive a bonus
  command: "give @player diamond 1"  # The command executed to give the player their bonus

# Message configuration
message:
  notification: "&aPlayer @player received their purchase: @product (@command)"  # Notification message when a player receives a purchase
  permission: "&cYou do not have permission to execute this command!"            # Message when a player lacks permission
  inventory: "&eEmpty your inventory to receive your purchase, and log in again!"  # Message when a player's inventory is Full
```

## 💡 Developer 

If any developer wants to contribute, just open a [PR](https://github.com/sebastianjnuwu/craftcourier/pulls). Follow the steps below to compile the plugin. Remember, we are using Ubuntu:

• Install Git:
```bash
apt install git -y 
```

• Install Maven:
```bash
apt install maven -y 
```

• Clone the repository:
```bash
git clone https://github.com/sebastianjnuwu/craftcourier
```

• Enter the folder:
```bash
cd craftcourier
```

• Compile the plugin:
```bash
mvn install 
```

## 📊 Bstats

![](https://bstats.org/signatures/bukkit/craftcourier.svg)