import BalanceBox from "../../components/BalanceBox";
import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  QRcodeContainer,
  QRcodeInfoContainer,
  QRcodeInfoName,
  QRcodeInfoNameText,
} from "../styled";
import QRCode from "react-native-qrcode-svg";
import * as Clipboard from "expo-clipboard";
import { Alert } from "react-native";

interface PropsType {
  // 잔액
  balance: string;
  // 아이디
  id: string;
  // stack navigation
  navigation: any;
  // 잔액 set
  setBalance: (a: string) => void;
  // 새로운 인증 토큰 발급
  getNewToken: () => void;
}

// Component _ Home
const Home: React.FC<PropsType> = ({
  balance,
  id,
  navigation,
  setBalance,
  getNewToken,
}) => {
  // method
  const copyToClipboard = () => {
    Clipboard.setString(id);
    Alert.alert(`아이디 "${id}"를 클립보드에 복사하였습니다.`);
  };

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox
          category="파라솔 PAY"
          num={balance}
          setBalance={setBalance}
          getNewToken={getNewToken}
        />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          {/* QR 코드 정보 */}
          <QRcodeContainer>
            <QRCode value={id} size={190} />
            <QRcodeInfoContainer>
              <QRcodeInfoName onPress={copyToClipboard}>
                <QRcodeInfoNameText>{id}</QRcodeInfoNameText>
              </QRcodeInfoName>
            </QRcodeInfoContainer>
          </QRcodeContainer>
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="송금하기" navigation={navigation} />
          <BtnBox color="blue" text="QR 스캔" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Home;
