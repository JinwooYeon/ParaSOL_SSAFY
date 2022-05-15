import React, { useState, useEffect } from "react";
import { Text, StyleSheet } from "react-native";
import { BarCodeScanner } from "expo-barcode-scanner";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
} from "../styled";
import BalanceBox from "../../components/BalanceBox";
import BtnBox from "../../components/BtnBox";
import styled from "styled-components/native";

interface PropsType {
  // 잔액
  balance: string;
  // 송금할 주소 set
  setInfo: (a: string) => void;
  // stack navigation
  navigation: any;
  // 잔액 set
  setBalance: (a: string) => void;
  // 새로운 인증 토큰 발급
  getNewToken: () => void;
}

const Scanner: React.FC<PropsType> = ({
  balance,
  setInfo,
  navigation,
  setBalance,
  getNewToken,
}) => {
  // useState
  // 카메라 권한 허가 유무
  const [hasPermission, setHasPermission] = useState<any>(null);
  // 스캔 여부
  const [scanned, setScanned] = useState(false);

  // useEffect
  useEffect(() => {
    (async () => {
      const { status } = await BarCodeScanner.requestPermissionsAsync();
      setHasPermission(status === "granted");
    })();
  }, []);

  // method
  const handleBarCodeScanned = ({ data }: { data: string }) => {
    setScanned(true);
    setInfo(data);
    navigation.navigate("Transaction");
  };

  // if _ hasPermission
  if (hasPermission === null) {
    return <Text>Requesting for camera permission</Text>;
  }
  if (hasPermission === false) {
    return <Text>No access to camera</Text>;
  }

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox
          category="QR 스캔"
          num={balance}
          setBalance={setBalance}
          getNewToken={getNewToken}
        />
      </HeaderContainer>
      <ContentFooterContainer>
        <QRScannerContainer>
          {/* QR 스캔 공간 */}
          <BarCodeScanner
            onBarCodeScanned={scanned ? undefined : handleBarCodeScanned}
            style={StyleSheet.absoluteFillObject}
          />
        </QRScannerContainer>
        <FooterContainer>
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

const QRScannerContainer = styled(ContentContainer)`
  height: 75%;
`;

export default Scanner;
