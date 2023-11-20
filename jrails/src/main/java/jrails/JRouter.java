//JRouter.java
package jrails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JRouter {

    private List<Route> routes;

    public JRouter() {
        this.routes = new ArrayList<>();
    }

    public void addRoute(String verb, String path, Class clazz, String method) {
        Route route = new Route(verb, path, clazz, method);
        routes.add(route);
    }

    public String getRoute(String verb, String path) {
        for (Route route : routes) {
            if (route.matches(verb, path)) {
                return route.getControllerMethod();
            }
        }
        return null;
    }

    public Html route(String verb, String path, Map<String, String> params) {
        String routeInfo = getRoute(verb, path);
        if (routeInfo != null) {
            String[] parts = routeInfo.split("#");
            try {
                Class<?> clazz = Class.forName(parts[0]);
                Controller controller = (Controller) clazz.newInstance();
                return (Html) clazz.getMethod(parts[1], Map.class).invoke(controller, params);
            } catch (Exception e) {
                throw new UnsupportedOperationException("Error invoking controller method", e);
            }
        } else {
            throw new UnsupportedOperationException("No route found for " + verb + " " + path);
        }
    }

    private static class Route {
        private String verb;
        private String path;
        private Class clazz;
        private String method;

        public Route(String verb, String path, Class clazz, String method) {
            this.verb = verb;
            this.path = path;
            this.clazz = clazz;
            this.method = method;
        }

        public boolean matches(String verb, String path) {
            return this.verb.equals(verb) && this.path.equals(path);
        }

        public String getControllerMethod() {
            return clazz.getName() + "#" + method;
        }
    }
}
