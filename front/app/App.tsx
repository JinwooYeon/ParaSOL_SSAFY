import {
  NavigationContainer,
  DarkTheme,
  DefaultTheme,
} from "@react-navigation/native";
import React from "react";
import Tabs from "./navigation/Tabs";
import { useColorScheme } from "react-native";

export default function App() {
  const isDark = useColorScheme() === "dark";

  return (
    <NavigationContainer theme={isDark ? DarkTheme : DefaultTheme}>
      <Tabs />
    </NavigationContainer>
  );
}
