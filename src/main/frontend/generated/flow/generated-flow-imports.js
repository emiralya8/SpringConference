import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import 'Frontend/generated/jar-resources/ReactRouterOutletElement.tsx';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '7ea44d6302d563fe4aace78a6980fe1cac8693d3cca7e575ffdd156292e4c5cd') {
    pending.push(import('./chunks/chunk-e8aef5bcd661ea88901c24a1cff80287b35a7598c44ce88e556bb1d5aa2ac249.js'));
  }
  if (key === '9338c25eef66d97fc68b2c812b3745373d4e5e77a1fefc5caf5cf273d962f227') {
    pending.push(import('./chunks/chunk-e8aef5bcd661ea88901c24a1cff80287b35a7598c44ce88e556bb1d5aa2ac249.js'));
  }
  if (key === '46a900b126d93b6627ccef35c705c26968208254934c6d9de87aefe111bac262') {
    pending.push(import('./chunks/chunk-31ea524d437acb467cb86113c3b4df1756167d475bf6c8b73c60c65543c284b8.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}