import { useEffect } from "react";
import { useState } from "react";
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
  bankInfo?: any;
  navigate: (a: any) => void;
}

const ConnectedAccountBox: React.FC<PropsType> = ({ bankInfo, navigate }) => {
  const { bankImg, bankName, bankNum } = bankInfo;
  const [empty, setEmpty] = useState(true);

  const onPress = () => {
    navigate("Mypage");
  };

  useEffect(() => {
    if (bankImg + bankName + bankNum !== "") setEmpty(false);
  }, []);

  return (
    <ConnectedAccountContainer>
      <ConnectedAccountHeaderContainer empty={empty}>
        <ConnectedAccountHeaderText>연결된 계좌</ConnectedAccountHeaderText>
        <ConnectedAccountHeaderSetting>
          <ConnectedAccountHeaderSettingText onPress={onPress}>
            {empty ? "추가" : "관리"}
          </ConnectedAccountHeaderSettingText>
        </ConnectedAccountHeaderSetting>
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
