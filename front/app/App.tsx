import { NavigationContainer } from "@react-navigation/native";
import React, { useState } from "react";
import Header from "./components/Header";
import Tabs from "./navigation/Tabs";
import Login from "./screens/Login";

export default function App() {
  const [login, setLogin] = useState(false);

  if (login) {
    return (
      <NavigationContainer>
        <Header />
        <Tabs />
      </NavigationContainer>
    );
  } else {
    return <Login />;
  }
}
