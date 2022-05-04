import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import { useState } from "react";
import { SafeAreaProvider } from "react-native-safe-area-context";
import Header from "./components/Header";
import Tabs from "./navigation/Tabs";

export default function App() {
  const [login, setLogin] = useState(true);

  if (login) {
    return (
      <SafeAreaProvider>
        <NavigationContainer>
          <Header />
          <Tabs />
        </NavigationContainer>
      </SafeAreaProvider>
    );
  }
}
