import { NavigationContainer } from "@react-navigation/native";
import { SafeAreaProvider } from "react-native-safe-area-context";
import React, { useState } from "react";
import Logo from "./components/Logo";
import Tabs from "./navigation/Tabs";
import LoginStack from "./navigation/LoginStack";

export default function App() {
  const [login, setLogin] = useState(false);

  if (login) {
    return (
      <SafeAreaProvider>
        <NavigationContainer>
          <Logo />
          <Tabs setLogin={setLogin} />
        </NavigationContainer>
      </SafeAreaProvider>
    );
  } else {
    return (
      <SafeAreaProvider>
        <NavigationContainer>
          <Logo />
          <LoginStack setLogin={setLogin} />
        </NavigationContainer>
      </SafeAreaProvider>
    );
  }
}
