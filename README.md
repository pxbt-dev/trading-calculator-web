# Trading Position Size Calculator Web App 🌐

A Spring Boot web application for calculating optimal trading position sizes based on risk management principles.

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Templates-orange)

## Features ✨

- **🎯 Position Sizing**: Calculate max units that are able to be bought based on account size and risk tolerance
- **📊 Risk Analysis**: Risk/reward calculations
- **🌐 Web Interface**: Accessible from any device with a browser
- **📱 Responsive Design**: Works on desktop, tablet, and mobile
- **⚡ Single-Page**: Results display on the same page without reloading

## Live 🚀

**🌐**: [tradingpositionsizecalculator.com](https://www.tradingpositionsizecalculator.com/)

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.5.7, Maven
- **Frontend**: HTML5, CSS3, JavaScript
- **Deployment**: Railway (PaaS), Cloudflare (CDN/SSL)

## Future Roadmap 
- Bug fixes
  - clean code / structure up (.css for e.g)
  - Fix formatting issues on mobile
  - Add tab to calculate Short position
- improvements
  - Java version upgrade (17 → 21/LTS)
  - CSS architecture refactor (modular structure)
  - Android version in development (Java/Kotlin)
  - iOS version planned (Swift/SwiftUI)
  - Binance API integration for live market data
  - Progressive Web App (PWA) for enhanced mobile experience

## Quick Start

```bash
# Clone the repository
git clone https://github.com/pxbt-dev/trading-calculator-web.git

# Run locally
./mvnw spring-boot:run

# Visit http://localhost:8080

