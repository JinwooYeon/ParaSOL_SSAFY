import { NavigationContainer } from "@react-navigation/native";
import { SafeAreaProvider } from "react-native-safe-area-context";
import React, { useState } from "react";
import Header from "./components/Header";
import Tabs from "./navigation/Tabs";
import Login from "./screens/Login";

export default function App() {
  const [login, setLogin] = useState(false);

  if (login) {
    return (
      <SafeAreaProvider>
        <NavigationContainer>
          <Header />
          <Tabs />
        </NavigationContainer>
      </SafeAreaProvider>
    );
  } else {
    return <Login setLogin={setLogin} />;
  }
}
