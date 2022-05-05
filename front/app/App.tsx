import { NavigationContainer } from "@react-navigation/native";
import { SafeAreaProvider } from "react-native-safe-area-context";
import React, { useState } from "react";
import Logo from "./components/Logo";
import Tabs from "./navigation/Tabs";
import Login from "./screens/Login";

export default function App() {
  const [login, setLogin] = useState(false);

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
    return <Login setLogin={setLogin} />;
  }
}
