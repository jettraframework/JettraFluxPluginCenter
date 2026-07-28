package com.flux.example.pages.template;

import io.jettra.flux.pages.FluxBaseHandler;
import com.sun.net.httpserver.HttpExchange;
import io.jettra.flux.core.Widget;

import io.jettra.flux.widgets.ActionIcon;
import io.jettra.flux.widgets.Column;
import io.jettra.flux.widgets.Dashboard;
import io.jettra.flux.widgets.Footer;
import io.jettra.flux.widgets.Header;
import io.jettra.flux.widgets.Icon;
import io.jettra.flux.widgets.Left;
import io.jettra.flux.widgets.Paragraph;
import io.jettra.flux.widgets.Row;
import io.jettra.flux.widgets.Scaffold;
import io.jettra.flux.widgets.SidebarCategory;
import io.jettra.flux.widgets.SidebarLogo;
import io.jettra.flux.widgets.ThemeChanged;
import io.jettra.flux.widgets.Top;
import io.jettra.flux.widgets.WidgetLet;
import io.jettra.flux.widgets.NotificationTop;
import io.jettra.server.JettraServer;
import java.util.Map;


public abstract class TemplatePage extends FluxBaseHandler {

    protected abstract Widget buildCenter(HttpExchange exchange, Map<String, String> params, String currentTheme);

    public static NotificationTop getNotificationTop(String idnotification) {
        io.jettra.server.core.JettraContext ctx = io.jettra.server.core.JettraContext.getCurrent();
        if (ctx == null) return null;
        
        Map<String, NotificationTop> notifications = 
            (Map<String, NotificationTop>) ctx.get(io.jettra.server.core.JettraContext.Scope.SESSION, "template_notifications");
            
        if (notifications == null) {
            notifications = new java.util.HashMap<>();
            ctx.set(io.jettra.server.core.JettraContext.Scope.SESSION, "template_notifications", notifications);
        }
        
        NotificationTop nt = notifications.get(idnotification);
        if (nt == null) {
            nt = NotificationTop.of().binding(idnotification);
            notifications.put(idnotification, nt);
        }
        return nt;
    }

    @Override
    protected Widget buildUI(HttpExchange exchange, Map<String, String> params, String currentTheme) {
        String username = getLoggedUser(exchange);
        if (username == null || username.isEmpty()) {
            try {
                redirect(exchange, "/login");
            } catch (Exception e) {
            }
            return Column.of();
        }

        // Simulating CredentialFlux from context/session (or could be fetched from DB)
        // Usually you'd fetch this from JettraContext or DB based on username
        String userInitial = username.substring(0, 1).toUpperCase();

        io.jettra.flux.model.CredentialFlux credential = (io.jettra.flux.model.CredentialFlux) io.jettra.server.core.JettraContext.getCurrent().get(io.jettra.server.core.JettraContext.Scope.SESSION, "credentialFlux");
        String displayName = username;
        
        Widget photoWidget = io.jettra.flux.widgets.Avatar.label(userInitial).shape("circle")
            .modifier(new io.jettra.flux.core.Modifier().style("background-color:#3b82f6; color:white; font-weight:bold; margin-right:8px;"));

        if (credential != null) {
            displayName = credential.name() != null && !credential.name().isEmpty() ? credential.name() : username;
            if (credential.photo() != null && !credential.photo().isEmpty()) {
                photoWidget = io.jettra.flux.widgets.Avatar.image(credential.photo()).shape("circle")
                    .modifier(new io.jettra.flux.core.Modifier().style("margin-right:8px;"));
            } else {
                photoWidget = io.jettra.flux.widgets.Avatar.icon("fas fa-user").shape("circle")
                    .modifier(new io.jettra.flux.core.Modifier().style("background-color:#3b82f6; color:white; font-weight:bold; margin-right:8px;"));
            }
        }

        Widget customCss = Paragraph.of(io.jettra.flux.theme.OceanTheme.Template.CustomCSS + "\n" + io.jettra.flux.theme.OceanTheme.Template.CustomJS);

    



/**
Start Plugin: MiNuevoPlugin
**/
        WidgetLet ecommMenuMiNuevoPlugin = WidgetLet.of("E-Commerce").icon(Icon.HOME);
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("Dashboard").icon(Icon.CHART_LINE).url(JettraServer.resolvePath("/dashboard")));
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("Product Overview").icon(Icon.SEARCH).url(JettraServer.resolvePath("/product-overview")));
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("Product List").icon(Icon.LIST).url(JettraServer.resolvePath("/product-list")));
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("New Product").icon(Icon.PLUS).url(JettraServer.resolvePath("/new-product")));
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("Shopping Cart").icon(Icon.SHOPPING_CART).url(JettraServer.resolvePath("/shopping-cart")));
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("Checkout Form").icon(Icon.CHECK).url(JettraServer.resolvePath("/checkout-form")));
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("Order History").icon(Icon.HISTORY).url(JettraServer.resolvePath("/order-history")));
        ecommMenuMiNuevoPlugin.add(WidgetLet.of("Order Summary").icon(Icon.RECEIPT).url(JettraServer.resolvePath("/order-summary")));
// Example
        WidgetLet exampleMenuMiNuevoPlugin = WidgetLet.of("Example").icon(Icon.COG);
        exampleMenuMiNuevoPlugin.add(WidgetLet.of("Person").icon(Icon.CHART_LINE).url(JettraServer.resolvePath("/person")));
        exampleMenuMiNuevoPlugin.add(WidgetLet.of("Reglas").icon(Icon.RECEIPT).url(JettraServer.resolvePath("/reglas")));

        // Apps
        WidgetLet appsMenuMiNuevoPlugin = WidgetLet.of("Apps").icon(Icon.TH_LARGE);
        appsMenuMiNuevoPlugin.add(WidgetLet.of("Chat").icon(Icon.COMMENTS).url(JettraServer.resolvePath("/chat")));
        appsMenuMiNuevoPlugin.add(WidgetLet.of("Mail Inbox").icon(Icon.ENVELOPE).url(JettraServer.resolvePath("/mail-inbox")));
        appsMenuMiNuevoPlugin.add(WidgetLet.of("Task List").icon(Icon.CHECK).url(JettraServer.resolvePath("/tasklist")));
        appsMenuMiNuevoPlugin.add(WidgetLet.of("Files").icon(Icon.FOLDER).url(JettraServer.resolvePath("/files")));
        appsMenuMiNuevoPlugin.add(WidgetLet.of("File").icon(Icon.FILE).url(JettraServer.resolvePath("/file")));

        WidgetLet userManagementMenuMiNuevoPlugin = WidgetLet.of("User Management").icon(Icon.USER);
        userManagementMenuMiNuevoPlugin.add(WidgetLet.of("Profile List").icon(Icon.USERS).url(JettraServer.resolvePath("/profile-list")));
        userManagementMenuMiNuevoPlugin.add(WidgetLet.of("Basic Information").icon(Icon.INFO_CIRCLE).url(JettraServer.resolvePath("/profile-basic-information")));

        WidgetLet uiKitMenuMiNuevoPlugin = WidgetLet.of("UI Components").icon(Icon.LAYER_GROUP);
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Input").icon(Icon.EDIT).url(JettraServer.resolvePath("/input")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Forms").icon(Icon.ALIGN_JUSTIFY).url(JettraServer.resolvePath("/forms")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Icon").icon(Icon.ALIGN_JUSTIFY).url(JettraServer.resolvePath("/icon")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Button Demo").icon(Icon.MOUSE_POINTER).url(JettraServer.resolvePath("/button-demo")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Table").icon(Icon.CHART_BAR).url(JettraServer.resolvePath("/table")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("DataView").icon(Icon.LIST).url(JettraServer.resolvePath("/dataview")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Tree").icon(Icon.SITEMAP).url(JettraServer.resolvePath("/tree")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Panel").icon(Icon.TH_LARGE).url(JettraServer.resolvePath("/panel")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Overlay").icon(Icon.CLONE).url(JettraServer.resolvePath("/overlay")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Media").icon(Icon.IMAGE).url(JettraServer.resolvePath("/media")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Menu").icon(Icon.BARS).url(JettraServer.resolvePath("/menu")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Message").icon(Icon.COMMENTS).url(JettraServer.resolvePath("/message")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Charts").icon(Icon.CHART_PIE).url(JettraServer.resolvePath("/charts")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Timeline").icon(Icon.CALENDAR_ALT).url(JettraServer.resolvePath("/timeline")));
        uiKitMenuMiNuevoPlugin.add(WidgetLet.of("Misc").icon(Icon.CUBE).url(JettraServer.resolvePath("/misc")));

        WidgetLet uiLayoutMenuMiNuevoPlugin = WidgetLet.of("Layout & Grid").icon(Icon.WINDOW_MAXIMIZE);
        uiLayoutMenuMiNuevoPlugin.add(WidgetLet.of("Card Demo").icon(Icon.WINDOW_MAXIMIZE).url(JettraServer.resolvePath("/card-demo")));
        uiLayoutMenuMiNuevoPlugin.add(WidgetLet.of("Grid Layout").icon(Icon.BORDER_ALL).url(JettraServer.resolvePath("/grid-demo")));

        WidgetLet rootUiKitMenuMiNuevoPlugin = WidgetLet.of("UI Kit").icon(Icon.LAYER_GROUP);
        rootUiKitMenuMiNuevoPlugin.add(uiKitMenuMiNuevoPlugin);
        rootUiKitMenuMiNuevoPlugin.add(uiLayoutMenuMiNuevoPlugin);

    

/**
End Plugin: MiNuevoPlugin
**/

/**
Start Plugin: OtherPlugin
**/
        WidgetLet ecommMenuOtherPlugin = WidgetLet.of("E-Commerce").icon(Icon.HOME);
        ecommMenuOtherPlugin.add(WidgetLet.of("Dashboard").icon(Icon.CHART_LINE).url(JettraServer.resolvePath("/dashboard")));
        ecommMenuOtherPlugin.add(WidgetLet.of("Product Overview").icon(Icon.SEARCH).url(JettraServer.resolvePath("/product-overview")));
        ecommMenuOtherPlugin.add(WidgetLet.of("Product List").icon(Icon.LIST).url(JettraServer.resolvePath("/product-list")));
        ecommMenuOtherPlugin.add(WidgetLet.of("New Product").icon(Icon.PLUS).url(JettraServer.resolvePath("/new-product")));
        ecommMenuOtherPlugin.add(WidgetLet.of("Shopping Cart").icon(Icon.SHOPPING_CART).url(JettraServer.resolvePath("/shopping-cart")));
        ecommMenuOtherPlugin.add(WidgetLet.of("Checkout Form").icon(Icon.CHECK).url(JettraServer.resolvePath("/checkout-form")));
        ecommMenuOtherPlugin.add(WidgetLet.of("Order History").icon(Icon.HISTORY).url(JettraServer.resolvePath("/order-history")));
        ecommMenuOtherPlugin.add(WidgetLet.of("Order Summary").icon(Icon.RECEIPT).url(JettraServer.resolvePath("/order-summary")));
// Example
        WidgetLet exampleMenuOtherPlugin = WidgetLet.of("Example").icon(Icon.COG);
        exampleMenuOtherPlugin.add(WidgetLet.of("Person").icon(Icon.CHART_LINE).url(JettraServer.resolvePath("/person")));
        exampleMenuOtherPlugin.add(WidgetLet.of("Reglas").icon(Icon.RECEIPT).url(JettraServer.resolvePath("/reglas")));

        // Apps
        WidgetLet appsMenuOtherPlugin = WidgetLet.of("Apps").icon(Icon.TH_LARGE);
        appsMenuOtherPlugin.add(WidgetLet.of("Chat").icon(Icon.COMMENTS).url(JettraServer.resolvePath("/chat")));
        appsMenuOtherPlugin.add(WidgetLet.of("Mail Inbox").icon(Icon.ENVELOPE).url(JettraServer.resolvePath("/mail-inbox")));
        appsMenuOtherPlugin.add(WidgetLet.of("Task List").icon(Icon.CHECK).url(JettraServer.resolvePath("/tasklist")));
        appsMenuOtherPlugin.add(WidgetLet.of("Files").icon(Icon.FOLDER).url(JettraServer.resolvePath("/files")));
        appsMenuOtherPlugin.add(WidgetLet.of("File").icon(Icon.FILE).url(JettraServer.resolvePath("/file")));

        WidgetLet userManagementMenuOtherPlugin = WidgetLet.of("User Management").icon(Icon.USER);
        userManagementMenuOtherPlugin.add(WidgetLet.of("Profile List").icon(Icon.USERS).url(JettraServer.resolvePath("/profile-list")));
        userManagementMenuOtherPlugin.add(WidgetLet.of("Basic Information").icon(Icon.INFO_CIRCLE).url(JettraServer.resolvePath("/profile-basic-information")));

        WidgetLet uiKitMenuOtherPlugin = WidgetLet.of("UI Components").icon(Icon.LAYER_GROUP);
        uiKitMenuOtherPlugin.add(WidgetLet.of("Input").icon(Icon.EDIT).url(JettraServer.resolvePath("/input")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Forms").icon(Icon.ALIGN_JUSTIFY).url(JettraServer.resolvePath("/forms")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Icon").icon(Icon.ALIGN_JUSTIFY).url(JettraServer.resolvePath("/icon")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Button Demo").icon(Icon.MOUSE_POINTER).url(JettraServer.resolvePath("/button-demo")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Table").icon(Icon.CHART_BAR).url(JettraServer.resolvePath("/table")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("DataView").icon(Icon.LIST).url(JettraServer.resolvePath("/dataview")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Tree").icon(Icon.SITEMAP).url(JettraServer.resolvePath("/tree")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Panel").icon(Icon.TH_LARGE).url(JettraServer.resolvePath("/panel")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Overlay").icon(Icon.CLONE).url(JettraServer.resolvePath("/overlay")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Media").icon(Icon.IMAGE).url(JettraServer.resolvePath("/media")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Menu").icon(Icon.BARS).url(JettraServer.resolvePath("/menu")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Message").icon(Icon.COMMENTS).url(JettraServer.resolvePath("/message")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Charts").icon(Icon.CHART_PIE).url(JettraServer.resolvePath("/charts")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Timeline").icon(Icon.CALENDAR_ALT).url(JettraServer.resolvePath("/timeline")));
        uiKitMenuOtherPlugin.add(WidgetLet.of("Misc").icon(Icon.CUBE).url(JettraServer.resolvePath("/misc")));

        WidgetLet uiLayoutMenuOtherPlugin = WidgetLet.of("Layout & Grid").icon(Icon.WINDOW_MAXIMIZE);
        uiLayoutMenuOtherPlugin.add(WidgetLet.of("Card Demo").icon(Icon.WINDOW_MAXIMIZE).url(JettraServer.resolvePath("/card-demo")));
        uiLayoutMenuOtherPlugin.add(WidgetLet.of("Grid Layout").icon(Icon.BORDER_ALL).url(JettraServer.resolvePath("/grid-demo")));

        WidgetLet rootUiKitMenuOtherPlugin = WidgetLet.of("UI Kit").icon(Icon.LAYER_GROUP);
        rootUiKitMenuOtherPlugin.add(uiKitMenuOtherPlugin);
        rootUiKitMenuOtherPlugin.add(uiLayoutMenuOtherPlugin);

    

/**
End Plugin: OtherPlugin
**/

        Widget menu = Left.of(
                SidebarLogo.of(Icon.LAYER_GROUP, "Ocean"),
                SidebarCategory.of("Navigation"),
               
                ecommMenuMiNuevoPlugin,
                exampleMenuMiNuevoPlugin,
                appsMenuMiNuevoPlugin,
                userManagementMenuMiNuevoPlugin,
                uiKitMenuMiNuevoPlugin,
                uiLayoutMenuMiNuevoPlugin,
                rootUiKitMenuMiNuevoPlugin,
                ecommMenuOtherPlugin,
                exampleMenuOtherPlugin,
                appsMenuOtherPlugin,
                userManagementMenuOtherPlugin,
                uiKitMenuOtherPlugin,
                uiLayoutMenuOtherPlugin,
                rootUiKitMenuOtherPlugin
        ).modifier(new io.jettra.flux.core.Modifier().cssClass("professional-left"));

        // User Profile Dropdown
        Widget profileTrigger = Row.of(
            photoWidget,
            io.jettra.flux.widgets.Span.of("").modifier(new io.jettra.flux.core.Modifier().style("font-weight:bold;")),
            Icon.of("fas fa-caret-down").modifier(new io.jettra.flux.core.Modifier().style("margin-left:5px;"))
        ).modifier(new io.jettra.flux.core.Modifier().style("align-items:center; cursor:pointer;").attribute("title", displayName));

        Widget profileMenu = ((io.jettra.flux.widgets.OverlayMenu) io.jettra.flux.widgets.OverlayMenu.of(
                WidgetLet.of("Logout").icon(Icon.SIGN_OUT_ALT).url(JettraServer.resolvePath("/login?logout=true"))
        ).trigger(profileTrigger)).alignRight();

        // Language Switcher
        String cookieHeader = exchange.getRequestHeaders().getFirst("Cookie");
        String currentLang = "en";
        if (cookieHeader != null && cookieHeader.contains("jettra_lang=es")) {
            currentLang = "es";
        }

        Widget langTrigger;
        io.jettra.flux.widgets.WidgetLet langOption;
        if ("es".equals(currentLang)) {
            langTrigger = io.jettra.flux.widgets.Span.of("🇪🇸").modifier(new io.jettra.flux.core.Modifier().attribute("title", "Español").style("cursor:pointer; font-size:1.2rem;"));
            langOption = (io.jettra.flux.widgets.WidgetLet) io.jettra.flux.widgets.WidgetLet.of("🇺🇸").url("?change_lang=en");
        } else {
            langTrigger = io.jettra.flux.widgets.Span.of("🇺🇸").modifier(new io.jettra.flux.core.Modifier().attribute("title", "English").style("cursor:pointer; font-size:1.2rem;"));
            langOption = (io.jettra.flux.widgets.WidgetLet) io.jettra.flux.widgets.WidgetLet.of("🇪🇸").url("?change_lang=es");
        }

        Widget langSwitcher = ((io.jettra.flux.widgets.OverlayMenu) io.jettra.flux.widgets.OverlayMenu.of(langOption).trigger(langTrigger)).alignRight();

        NotificationTop globalNotif = getNotificationTop("global_notif").type(NotificationTop.NotificationTopType.GLOBAL).icon(Icon.of("fas fa-globe"));
        NotificationTop personalNotif = getNotificationTop("personal_notif").type(NotificationTop.NotificationTopType.PERSONAL).icon(Icon.of("fas fa-envelope"));
        NotificationTop channelNotif = getNotificationTop("channel_notif").type(NotificationTop.NotificationTopType.CHANNEL).channel("admin_channel").icon(Icon.of("fas fa-bullhorn"));

        Widget topBar = Top.of(
                Row.of(
                        ActionIcon.of(Icon.BARS + " top-bars-icon", "toggleSidebar()"),
                        Header.of(4, "Dashboard").modifier(new io.jettra.flux.core.Modifier().cssClass("top-dashboard-title"))
                ).modifier(new io.jettra.flux.core.Modifier().cssClass("top-left-section")),
                Row.of(
                        Icon.of(Icon.SEARCH),
                        globalNotif,
                        personalNotif,
                        channelNotif,
                        langSwitcher,
                        ThemeChanged.of().current(currentTheme),
                        profileMenu
                ).modifier(new io.jettra.flux.core.Modifier().cssClass("top-right-section").style("gap: 15px; align-items: center;"))
        );

        // --- Center Content from Subclass ---
        Widget centerContent = Column.of(
                customCss,
                buildCenter(exchange, params, currentTheme)
        ).modifier(new io.jettra.flux.core.Modifier().cssClass("professional-center espresso-center"));

        // --- Footer ---
        Widget footerContent = Footer.of(
                Paragraph.of("© 2026 JettraStack - JettraFlux")
        );

        Widget body = Dashboard.of(
                topBar,
                menu,
                centerContent,
                footerContent
        );

        return Scaffold.of().body(body);
    }
}
