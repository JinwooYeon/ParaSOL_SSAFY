import { useEffect, useState } from "react";
import {
  ConnectedAccountContainer,
  ConnectedAccountContentContainer,
  ConnectedAccountDetailBank,
  ConnectedAccountDetailContainer,
  ConnectedAccountDetailImg,
  ConnectedAccountDetailNum,
  ConnectedAccountHeaderContainer,
  ConnectedAccountHeaderSetting,
  ConnectedAccountHeaderSettingText,
  ConnectedAccountHeaderText,
} from "../screens/styled";

interface PropsType {
  // 계좌 연결 정보
  bankInfo?: any;
  // stack navigation
  navigation: any;
  // 내 정보 페이지 여부
  myPage?: boolean;
}

// Component _ ConnectedAccountBox
const ConnectedAccountBox: React.FC<PropsType> = ({
  bankInfo,
  navigation: { navigate },
  myPage,
}) => {
  // const
  // 계좌 연결 정보 비구조화
  const { bankImg, bankName, bankNum } = bankInfo;
  // 계좌 연결 정보 존재 여부
  const [empty, setEmpty] = useState<boolean>(true);

  // method
  const onPress = () => {
    navigate("Mypage");
  };

  // useEffect
  useEffect(() => {
    if (bankImg + bankName + bankNum !== "") setEmpty(false);
  }, []);

  return (
    <ConnectedAccountContainer>
      <ConnectedAccountHeaderContainer empty={empty}>
        <ConnectedAccountHeaderText>연결된 계좌</ConnectedAccountHeaderText>
        {!myPage && (
          <ConnectedAccountHeaderSetting>
            <ConnectedAccountHeaderSettingText onPress={onPress}>
              {empty ? "추가" : "관리"}
            </ConnectedAccountHeaderSettingText>
          </ConnectedAccountHeaderSetting>
        )}
      </ConnectedAccountHeaderContainer>
      {empty ? null : (
        <ConnectedAccountContentContainer>
          <ConnectedAccountDetailImg source={{ uri: bankImg }} />
          <ConnectedAccountDetailContainer>
            <ConnectedAccountDetailBank>{bankName}</ConnectedAccountDetailBank>
            <ConnectedAccountDetailNum>{bankNum}</ConnectedAccountDetailNum>
          </ConnectedAccountDetailContainer>
        </ConnectedAccountContentContainer>
      )}
    </ConnectedAccountContainer>
  );
};

export default ConnectedAccountBox;
