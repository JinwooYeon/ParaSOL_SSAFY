import AccountBalanceGET from "components/account/accountBalanceGET";
import AccountGET from "components/account/accountGET";
import AccountHistoryGET from "components/account/accountHistoryGET";
import { Box, Paper, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";
import AccountDepositPOST from "components/account/accountDepositPOST";
import AccountWithdrawPOST from "components/account/accountWithdrawPOST";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  color: theme.palette.text.secondary,
  fontSize: 17,
}));

export const Account = () => {
  return (
    <>
      <Box sx={{ width: "100%" }}>
        <Stack spacing={2}>
          {/* 계좌 목록 조회 */}
          <Item>
            <AccountGET />
          </Item>
          {/* 계좌 잔액 조회 */}
          <Item>
            <AccountBalanceGET />
          </Item>
          {/* 계좌 거래내역 조회 */}
          <Item>
            <AccountHistoryGET />
          </Item>
          {/* 입금 */}
          <Item>
            <AccountDepositPOST />
          </Item>
          {/* 출금 */}
          <Item>
            <AccountWithdrawPOST />
          </Item>
        </Stack>
      </Box>
    </>
  );
};
