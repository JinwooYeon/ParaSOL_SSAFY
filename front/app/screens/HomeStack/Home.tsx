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
  QRcodeInfoNum,
  QRcodeInfoNumText,
} from "../styled";
import QRCode from "react-native-qrcode-svg";

interface PropsType {
  // 잔액
  balance: string;
  // stack navigation
  navigation: any;
}

// Component _ Home
const Home: React.FC<PropsType> = ({ balance, navigation }) => {
  // let
  // 내 정보 QR 코드
  let qrCodeInfo = {
    name: "GS25 싸피점",
    num: "0000-1234-9876",
  };

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          {/* QR 코드 정보 */}
          <QRcodeContainer>
            <QRCode value={qrCodeInfo.num} size={190} />
            <QRcodeInfoContainer>
              <QRcodeInfoName>{qrCodeInfo.name}</QRcodeInfoName>
              <QRcodeInfoNum>
                <QRcodeInfoNumText onPress={() => console.log(qrCodeInfo.num)}>
                  {qrCodeInfo.num}
                </QRcodeInfoNumText>
              </QRcodeInfoNum>
            </QRcodeInfoContainer>
          </QRcodeContainer>
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="송금하기" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Home;
