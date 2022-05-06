import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { SafeAreaProvider } from "react-native-safe-area-context";
import React, { useState } from "react";
import Logo from "./components/Logo";
import Tabs from "./navigation/Tabs";
import Login from "./screens/Login";
import Register from "./screens/Register";

export default function App() {
  const [login, setLogin] = useState(false);

  const Stack = createNativeStackNavigator();

  if (login) {
    return (
      <SafeAreaProvider>
        <NavigationContainer>
          <Logo />
          <Tabs />
        </NavigationContainer>
      </SafeAreaProvider>
    );
  } else {
    return (
      <SafeAreaProvider>
        <NavigationContainer>
          <Stack.Navigator>
            <Stack.Screen name="Login">
              {(props) => <Login {...props} setLogin={setLogin} />}
            </Stack.Screen>
            {/* <Stack.Screen name="Login" component={Login} /> */}

            <Stack.Screen name="Register" component={Register} />
          </Stack.Navigator>
        </NavigationContainer>
      </SafeAreaProvider>
    );
  }
}
