import BalanceBox from "../../components/BalanceBox";
import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
} from "../styled";
import React, { useState, useEffect } from "react";
import { Text, View, StyleSheet, Button } from "react-native";
import { BarCodeScanner } from "expo-barcode-scanner";
// import { StyleSheet, Text, View, TouchableOpacity } from "react-native";
// import { Camera } from "expo-camera";

interface PropsType {
  balance: string;
  navigation: any;
}

const Transaction: React.FC<PropsType> = ({ balance, navigation }) => {
  const [hasPermission, setHasPermission] = useState<any>(null);
  const [scanned, setScanned] = useState(true);

  useEffect(() => {
    (async () => {
      const { status } = await BarCodeScanner.requestPermissionsAsync();
      setHasPermission(status === "granted");
    })();
  }, []);

  const handleBarCodeScanned = ({ type, data }: { type: any; data: any }) => {
    setScanned(true);
    console.log(
      `Bar code with type ${type} and data ${data} has been scanned!`
    );
  };

  if (hasPermission === null) {
    return <Text>Requesting for camera permission</Text>;
  }
  if (hasPermission === false) {
    return <Text>No access to camera</Text>;
  }
  //   const [hasPermission, setHasPermission] = useState(null);
  //   const [type, setType] = useState(Camera.Constants.Type.back);

  //   useEffect(() => {
  //     // (async () => {
  //     //   const { status } = await Camera.requestCameraPermissionsAsync();
  //     const { status } = Camera.requestCameraPermissionsAsync();
  //     setHasPermission(status === "granted");
  //     // })();
  //   }, []);

  //   if (hasPermission === null) {
  //     return <View />;
  //   }
  //   if (hasPermission === false) {
  //     return <Text>No access to camera</Text>;
  //   }
  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          <View style={styles.container}>
            <BarCodeScanner
              onBarCodeScanned={scanned ? undefined : handleBarCodeScanned}
              style={StyleSheet.absoluteFillObject}
            />
            {scanned && (
              <Button
                title={"Tap to Scan Again"}
                onPress={() => setScanned(false)}
              />
            )}
          </View>
          {/* <View style={styles.container}>
            <Camera style={styles.camera} type={type}>
              <View style={styles.buttonContainer}>
                <TouchableOpacity
                  style={styles.button}
                  onPress={() => {
                    setType(
                      type === Camera.Constants.Type.back
                        ? Camera.Constants.Type.front
                        : Camera.Constants.Type.back
                    );
                  }}
                >
                  <Text style={styles.text}> Flip </Text>
                </TouchableOpacity>
              </View>
            </Camera>
          </View> */}
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="QR 스캔" navigation={navigation} />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
          {/* <BtnBox color="red" text="초기화" navigation={navigation} /> */}
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "column",
    justifyContent: "center",
  },
});

// const styles = StyleSheet.create({
//   container: {
//     flex: 1,
//   },
//   camera: {
//     flex: 1,
//   },
//   buttonContainer: {
//     flex: 1,
//     backgroundColor: "transparent",
//     flexDirection: "row",
//     margin: 20,
//   },
//   button: {
//     flex: 0.1,
//     alignSelf: "flex-end",
//     alignItems: "center",
//   },
//   text: {
//     fontSize: 18,
//     color: "white",
//   },
// });

export default Transaction;
