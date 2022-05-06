import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { SafeAreaProvider } from "react-native-safe-area-context";
import React, { useState } from "react";
import Logo from "./components/Logo";
import Tabs from "./navigation/Tabs";
import Login from "./screens/User/Login";
import Register from "./screens/User/Register";
import ForgetPassword from "./screens/User/ForgetPassword";

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
          <Logo />
          <Stack.Navigator
            screenOptions={{
              headerShown: false,
            }}
          >
            <Stack.Screen name="Login">
              {(props) => <Login {...props} setLogin={setLogin} />}
            </Stack.Screen>
            <Stack.Screen name="Register" component={Register} />
            <Stack.Screen name="ForgetPassword" component={ForgetPassword} />
          </Stack.Navigator>
        </NavigationContainer>
      </SafeAreaProvider>
    );
  }
}
