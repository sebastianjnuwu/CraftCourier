---

# CHANGELOG

All significant changes to this project will be documented in this file.

## [1.0.0] - 2024-08-18
### Added
- First stable release of **CraftCourier**.
- Automatic delivery of items purchased from Minecraft virtual stores.
- Integration with **nLogin** to verify player login status.
- MySQL database configuration for storing and managing transactions.
- Webhook system for purchase notifications.
- Bonus system that rewards players with additional items after a specific number of purchases.
- Customizable messages for notifications, permissions, and full inventory alerts.
- Dependency relocation using the `maven-shade-plugin`.
- Support for **bstats** API integration for metrics collection.
- Initial compatibility with **Spigot** servers.
- Basic support for custom commands configurable via `config.yml`.

### Fixed
- Fixed minor bugs related to database connection during item delivery.
- Adjustments to the logic for checking full inventory before item delivery.

---
