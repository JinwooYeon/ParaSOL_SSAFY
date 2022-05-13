import { NavigationContainer } from "@react-navigation/native";
import { SafeAreaProvider } from "react-native-safe-area-context";
import React, { useEffect, useState } from "react";
import Logo from "./components/Logo";
import Tabs from "./navigation/Tabs";
import LoginStack from "./navigation/LoginStack";
import AsyncStorage from "@react-native-async-storage/async-storage";

export default function App() {
  // useState
  // 로그인 여부
  const [login, setLogin] = useState<boolean>(false);

  // method
  const getToken = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    if (accessToken != null) {
      setLogin(true);
    }
  };

  // useEffect
  useEffect(() => {
    getToken();
  }, []);

  if (login) {
    // 로그인
    return (
      <SafeAreaProvider>
        <NavigationContainer>
          <Logo />
          <Tabs setLogin={setLogin} />
        </NavigationContainer>
      </SafeAreaProvider>
    );
  } else {
    // 로그아웃
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
