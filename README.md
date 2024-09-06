# Embedding Web Applications

## 1. Embedding Web Applications Using iframe

**Concept:**  
An [iframe](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe) allows embedding web application built with Genesis platform within a host application, creating a separate browsing context. If you only need particular component you might prefer option 2 which allows more fine grained selection.

**Advantages:**
- Isolates the embedded application’s context, preventing conflicts with the host.
- Simple and quick to implement.

**Challenges:**
- Limited interaction between the host and embedded app.
- Performance and security issues, such as X-Frame-Options and cross-origin resource sharing (CORS) policies.

## 2. Including Web Applications as a Script Tag and Web Component

**Concept:**  
A web application or single component can be included as a script tag in the host application, and then registered as a Web Component (Custom Element) to be used within the host’s DOM.

**Implementation:**
- **Script Tag:** Add a `<script>` tag in the host’s HTML pointing to the external web app's JavaScript file.
- **Web Component:** The external application can be registered as a Web Component, allowing it to be used as a custom HTML element, e.g., `<external-app></external-app>`.

**Advantages:**
- Seamless integration with the host’s DOM.
- Allows for easy reuse of the component across different projects.
- Interaction between the host and embedded app.

## 3. Using Webpack 5 Module Federation

**Concept:**  
Genesis applications are compatible with Webpack 5 which introduced Module Federation, allowing multiple independent applications to share code and dependencies at runtime, enabling microfrontend architecture.

**Implementation:**
- Configure `ModuleFederationPlugin` in both the host and remote applications.
- The remote application exposes specific modules (e.g., components, utilities), which can be dynamically loaded into the host application.

**Advantages:**
- Enables true microfrontend architecture, allowing independent deployment and updates.
- Reduces redundancy by sharing common dependencies between applications.

## 4. Embedding in a React Application

**Script Tag + Web Component:**
- Genesis provides tools that allow you to bootstrap a React application that uses Genesis Components.
- You can find a sample application built in React that uses Genesis Components [here](client-react/README.md).
- React supports Custom Elements since v19: [React 19 - Support for Custom Elements](https://react.dev/blog/2024/04/25/react-19#support-for-custom-elements).

**Webpack Module Federation:**
- Configure Webpack Module Federation in the React application, allowing parts of another React or non-React app to be dynamically loaded.

**Iframe:**  
Similar to the general approach, an iframe can be embedded within a React component.

## 5. Embedding in an Angular Application

- Genesis provides tools that allow you to bootstrap an Angular application that uses Genesis Components.
    - You can find a sample application built in Angular that uses Genesis Components [here](client-angular/README.md).

**Iframe:**  
Embed external applications via iframe within Angular templates.

**Script Tag + Web Component:**
- Include external scripts in the `index.html` or as part of an Angular component.
- Utilize Angular’s support for Web Components (e.g., using `customElements.define()`).

**Webpack Module Federation:**
- Use Angular CLI’s support for Webpack 5 to configure Module Federation, allowing Angular apps to consume or expose modules dynamically.

## Conclusion

Including a web application inside another can be done through various methods like iframe, script tags with Web Components, Webpack 5 Module Federation, or specific frameworks like React and Angular. Each method has its advantages and challenges, and the choice depends on the specific requirements such as the need for isolation, ease of integration, or modularity.

## Full Technical Details

See our reference data on [Integrations](https://docs.genesis.global/docs/develop/client-capabilities/framework-integration/) for a full explanation of the possibilities for working with both Angular and React applications.
# Test results
BDD test results can be found [here](https://genesiscommunitysuccess.github.io/howto-ui-integrations-angular/test-results)
