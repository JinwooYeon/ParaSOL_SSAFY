import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import { useState } from "react";
import Header from "./components/Header";
import Tabs from "./navigation/Tabs";

export default function App() {
  const [login, setLogin] = useState(true);

  if (login) {
    return (
      <NavigationContainer>
        <Header />
        <Tabs />
      </NavigationContainer>
    );
  }
}
